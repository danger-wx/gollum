package com.dangerousarea.gollum.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    public final static int SUCCESS_CODE = 200;
    private final static String SUCCESS_MESSAGE = "success";
    private int code;
    private String message;
    private T data;

    public static <T> CommonResult<T> success(T data){
        return new CommonResult(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static <T> CommonResult<T> error(int errorCode) {
        return new CommonResult(errorCode, ErrorCodes.getMessageByCode(errorCode), null);
    }

    public static <T> CommonResult<T> error(int errorCode, String message) {
        return new CommonResult(errorCode, message, null);
    }
}
