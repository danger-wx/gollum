package com.dangerousarea.gollum.common.exceptions;

public class BusinessException extends  RuntimeException{

    public BusinessException(int code, String ex){
        super(ex);
        this.code = code;
    }


    private int code;

    public int getCode() {
        return code;
    }
}
