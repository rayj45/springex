package org.zerock.springex.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

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

    public int getSkip(){ //건너뛰기
        return (page - 1) * 10;
    }

} //class
