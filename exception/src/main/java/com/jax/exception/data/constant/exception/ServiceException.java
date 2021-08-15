package com.jax.exception.data.constant.exception;

public abstract class ServiceException extends Exception{
    protected String message;
    protected int StatusCode;
    protected String messageCode;

    public ServiceException(String message, int statusCode, String messageCode) {
        super();
        this.message = message;
        StatusCode = statusCode;
        this.messageCode = messageCode;
    }

    public ServiceException(String message, int statusCode) {
        super();
        this.message = message;
        StatusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public String getMessageCode() {
        return messageCode;
    }
}
