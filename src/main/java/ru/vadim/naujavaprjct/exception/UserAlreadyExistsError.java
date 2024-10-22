package ru.vadim.naujavaprjct.exception;

import ru.vadim.naujavaprjct.dto.CustomErrorDto;

public class UserAlreadyExistsError extends CustomErrorDto {

    public UserAlreadyExistsError(Long userId) {
        super(ErrorType.USER_ALREADY_EXISTS, String.format("Пользователь с таким ID = %s, уже существует", userId));
    }
}
