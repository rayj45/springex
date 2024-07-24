package org.zerock.springex.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoVO {

    private Long tno; //번호
    private String title; //제목
    private LocalDate dueDate; //마감기한
    private String writer; //작성자
    private boolean finished; //완료여부

} //class
