package org.zerock.springex.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {
    //제네릭을 사용 - 나중에 다른 종류의 객체를 이용하여 dto를 구성할 수 있도록 위함

    private int page; //페이지
    private int size; //사이즈
    private int total; //총계

    private int start; //시작페이지
    private int end;   //끝페이지

    private boolean prev; //이전페이지 여부
    private boolean next; //다음페이지 여부

    private List<E> dtoList; //특정 객체를 list로 받아와 dtoList라는 이름으로 저장(dto객체를 받아오겠지?)

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total){

        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;

        this.end =   (int)(Math.ceil(this.page / 10.0 )) *  10;

        this.start = this.end - 9;

        int last =  (int)(Math.ceil((total/(double)size)));

        this.end =  end > last ? last: end;

        this.prev = this.start > 1;

        this.next =  total > this.end * this.size;

    }

} //class
