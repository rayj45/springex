package org.zerock.springex.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.zerock.springex.dto.PageRequestDTO;
import org.zerock.springex.dto.PageResponseDTO;
import org.zerock.springex.dto.TodoDTO;

import java.time.LocalDate;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml") //의존성 주입 설정파일 위치
public class TodoServiceTests {

    @Autowired
    private TodoService todoService;

    @Test
    public void testRegister(){
        TodoDTO todoDTO = TodoDTO.builder()
                .title("Test...")
                .dueDate(LocalDate.now())
                .writer("user1")
                .build();

    todoService.register(todoDTO);
    log.info(todoDTO);
    }

    @Test
    public void testPaging(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<TodoDTO> responseDTO = todoService.getList(pageRequestDTO); //페이징 객체 생성

        log.info(responseDTO);

        responseDTO.getDtoList().stream().forEach(todoDTO -> log.info(todoDTO)); //responseDTO의 dtoList변수를 활용하여 각 dto객체를 log출력

    }




} //class
