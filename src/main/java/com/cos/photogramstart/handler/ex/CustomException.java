package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomException extends RuntimeException{

    // 객체를 구분할 때
    private static final long serialVersionUID=1L;

    private Map<String, String> errorMap;

    public CustomException(String message) {
        super(message);
        this.errorMap = errorMap;
    }

}
