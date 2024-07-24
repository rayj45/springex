package org.zerock.springex.controller.formatter;

import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class CheckboxFormatter implements Formatter<Boolean> {
    //수정 시 화면에서 체크박스를 이용 시 값을 on, off로 발송 -> 0,1로 format해서 발송해야

    @Override
    public Boolean parse(String text, Locale locale) throws ParseException {
        if (text == null){

            return null;
        }
        return text.equals("on");
    }

    @Override
    public String print(Boolean object, Locale locale) {
        return object.toString();
    }


}
