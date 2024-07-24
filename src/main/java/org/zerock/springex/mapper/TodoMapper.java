package org.zerock.springex.mapper;

import org.zerock.springex.domain.TodoVO;
import org.zerock.springex.dto.PageRequestDTO;

import java.util.List;

public interface TodoMapper { //Mapper는 vo를 받아 쿼리실행

    String getTime();

    void insert(TodoVO todoVO); //삽입

    List<TodoVO> selectAll(); //전체조회
    
    TodoVO selectOne(Long tno); //상세조회

    void delete(Long tno); //삭제
    
    void update(TodoVO todoVO); //수정

    List<TodoVO> selectList(PageRequestDTO pageRequestDTO); //페이지조회
    
    int getCount(PageRequestDTO pageRequestDTO); //페이지개수조회

} //interface
