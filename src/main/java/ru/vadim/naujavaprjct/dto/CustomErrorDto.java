package ru.vadim.naujavaprjct.dto;

import ru.vadim.naujavaprjct.exception.ErrorType;

public class CustomErrorDto extends Throwable{
    private ErrorType errorType;
    private String message;

    public CustomErrorDto(ErrorType errorType, String message) {
        this.errorType = errorType;
        this.message = message;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
