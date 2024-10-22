package ru.vadim.naujavaprjct.exception;

import ru.vadim.naujavaprjct.dto.CustomErrorDto;

public class UserAlreadyExistsError extends CustomErrorDto {

    public UserAlreadyExistsError(String username) {
        super(ErrorType.USER_ALREADY_EXISTS, String.format("Пользователь с таким username = %s, уже существует", username));
    }
}
