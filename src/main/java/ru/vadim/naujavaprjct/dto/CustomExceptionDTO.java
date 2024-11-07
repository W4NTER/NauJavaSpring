package ru.vadim.naujavaprjct.dto;

import ru.vadim.naujavaprjct.exception.ExceptionType;

public record CustomExceptionDTO(
        ExceptionType exceptionType,
        String message) {

    public CustomExceptionDTO(String message) {
        this(ExceptionType.ANY_EXCEPTION, message);
    }
}
