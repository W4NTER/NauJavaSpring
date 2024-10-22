package ru.vadim.naujavaprjct.exception;

import ru.vadim.naujavaprjct.dto.CustomExceptionDto;

public class UserNotFoundException extends CustomExceptionDto {

    public UserNotFoundException(Long userId) {
        super(ExceptionType.USER_NOT_FOUND, "Пользователь с ID " + userId + " не найден.");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
