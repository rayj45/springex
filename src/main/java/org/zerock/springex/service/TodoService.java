package org.zerock.springex.service;


import org.zerock.springex.domain.TodoVO;
import org.zerock.springex.dto.PageRequestDTO;
import org.zerock.springex.dto.PageResponseDTO;
import org.zerock.springex.dto.TodoDTO;

import java.util.List;

public interface TodoService { //service는 dto를 받아 쿼리날림

    void register(TodoDTO todoDTO); //등록

    List<TodoDTO> getAll(); //전체조회
    
    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO); //페이지조회
    
    TodoDTO getOne(Long tno); //상세조회
    
    void remove(Long tno); //삭제

    void modify(TodoDTO todoDTO); //수정


    
} //class
