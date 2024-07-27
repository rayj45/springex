package org.zerock.springex.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    @Min(value = 1)
    @Positive
    private int page = 1; //페이지

    @Builder.Default
    @Min(value = 10)
    @Max(value = 100)
    @Positive
    private int size = 10; //페이지 내 데이터 사이즈

    private String link; //링크

    //검색
    private String[] types; //검색타입

    private String keyword; //검색키워드

    private boolean finished; //검색 완료여부

    private LocalDate from; //검색 시작일자

    private LocalDate to; //검색 종료일자



    public int getSkip(){ //건너뛰기
        return (page - 1) * 10;
    }

    public String getLink() {
        StringBuilder builder = new StringBuilder();

        builder.append("page=" + this.page);

        builder.append("&size=" + this.size);

        if(finished){
            builder.append("&finished=on");
        }

        if(types != null && types.length > 0){
            for (int i = 0; i < types.length ; i++) {
                builder.append("&types=" + types[i]);
            }
        }

        if(keyword != null){
            try { //한글 사용하는 keyword(검색키워드) 변수는 URLEncoder를 이용하여 링크로 처리
                builder.append("&keyword=" + URLEncoder.encode(keyword,"UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        if(from != null){
            builder.append("&from=" + from.toString());
        }

        if(to != null){
            builder.append("&to=" + to.toString());
        }

        return builder.toString();
    } //getLink

/*
    public String getLink(){
        if (link == null){
            StringBuilder builder = new StringBuilder();
            builder.append("page=" + this.page);
            builder.append("&size=" + this.size);
            link = builder.toString(); //page와 size정보를 전달받고 반환
        }
        return  link;
    } //getLink
*/
    public boolean checkType(String type){

        if (types == null || types.length == 0){ // || : or 조건
            return false; //검색조건이 비어있으면 false반환
        }
        return Arrays.stream(types).anyMatch(type::equals);
        //검색조건이 들어있다면 -> 매개변수로 전달받은 type과 일치하는지 여부를 확인하여 하나라도 일치한다면 true를, 없다면 false를 반환(anyMatch)
    }


} //class
