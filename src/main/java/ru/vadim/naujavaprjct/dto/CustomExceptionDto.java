package ru.vadim.naujavaprjct.dto;

import ru.vadim.naujavaprjct.exception.ExceptionType;

public class CustomExceptionDto extends RuntimeException {
    private ExceptionType exceptionType;
    private String message;

    public CustomExceptionDto(ExceptionType exceptionType, String message) {
        this.exceptionType = exceptionType;
        this.message = message;
    }

    public CustomExceptionDto(String message) {
        this.message = message;
    }

    public ExceptionType getErrorType() {
        return exceptionType;
    }

    public void setErrorType(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static CustomExceptionDto create(Throwable e) {
        return new CustomExceptionDto(e.getMessage());
    }

    public static CustomExceptionDto create(String message) {
        return new CustomExceptionDto(message);
    }
}
