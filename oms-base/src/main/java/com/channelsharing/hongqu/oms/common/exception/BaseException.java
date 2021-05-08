package com.channelsharing.hongqu.oms.common.exception;

import com.channelsharing.hongqu.oms.common.enums.CustomHttpStatus;

/**
 * Created by liuhangjun on 2017/12/7.
 */
public class BaseException extends RuntimeException {
    
    private static final long serialVersionUID = 2000044369617115727L;
    
    //http status code, default code is 600.
    private CustomHttpStatus status = CustomHttpStatus.DEFAULT_CUSTOM_STATUS;
    
    private String error = "System controllable error";
    
    public BaseException(String message) {
        super(message);
    }
    
    public BaseException(String message, CustomHttpStatus status){
        super(message);
        this.status = status;
    }
    
    public CustomHttpStatus getStatus() {
        return status;
    }
    
    public void setStatus(CustomHttpStatus status) {
        this.status = status;
    }
    
    public String getError() {
        return error;
    }
    
    public void setError(String error) {
        this.error = error;
    }
}
