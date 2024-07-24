package org.zerock.springex.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.springex.domain.TodoVO;
import org.zerock.springex.dto.PageRequestDTO;
import org.zerock.springex.dto.PageResponseDTO;
import org.zerock.springex.dto.TodoDTO;
import org.zerock.springex.mapper.TodoMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    private final TodoMapper todoMapper;

    private final ModelMapper modelMapper; //dto를 받아서 vo를 반환하는 역할 -> mapper에서 vo를 받아 쿼리 수행


    @Override
    public void register(TodoDTO todoDTO) {

        log.info(modelMapper);

        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class); //service에서는 dto를 받아 mapper를 통해 vo로 바꾸고

        log.info(todoVO);

        todoMapper.insert(todoVO); //vo를 날려 쿼리실행
    }

    @Override
    public List<TodoDTO> getAll() {

        List<TodoDTO> dtoList = todoMapper.selectAll().stream() //todoMapper - selectAll메서드를 통해 리스트를 조회(vo리스트 값을 받은 상태) -> stream을 통해 자른다
                .map(vo -> modelMapper.map(vo, TodoDTO.class)) //modelmapper를 사용하여 vo를 dto로 변환(stream때문에 낱개별로 바꿔주기 가능)한 값을
                .collect(Collectors.toList()); //collect를 통해 리스트로 수집하여 전달

    return dtoList;
    }

    @Override
    public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {

        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
        List<TodoDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());

        int total = todoMapper.getCount(pageRequestDTO);

        PageResponseDTO<TodoDTO> pageResponseDTO = PageResponseDTO.<TodoDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(pageRequestDTO)
                .build();

        return pageResponseDTO;

    }


    @Override
    public TodoDTO getOne(Long tno) {

        TodoVO todoVO = todoMapper.selectOne(tno);

        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);

        return todoDTO;
    }

    @Override
    public void remove(Long tno) {

        todoMapper.delete(tno);

    }

    @Override
    public void modify(TodoDTO todoDTO) {

        //서비스로부터 dto를 받았음. 수정하려면 todomapper를 통해서 바꿔야하므로 dto를 vo로 바꿔서 줘야함. modelmapper를 통해 변환하고 변환한 vo를 todomapper.update
        //mapper.xml에서 업데이터 수행.
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

        todoMapper.update(todoVO);
    }

} //class
