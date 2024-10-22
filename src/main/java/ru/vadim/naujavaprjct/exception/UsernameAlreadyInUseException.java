package ru.vadim.naujavaprjct.exception;

import ru.vadim.naujavaprjct.dto.CustomExceptionDto;

public class UsernameAlreadyInUseException extends CustomExceptionDto {

    public UsernameAlreadyInUseException(String username) {
        super(ExceptionType.USERNAME_ALREADY_IN_USE, String.format("Пользователь с таким username = %s, уже существует", username));
    }
}
