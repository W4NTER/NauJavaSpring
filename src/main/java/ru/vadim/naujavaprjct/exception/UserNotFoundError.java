package ru.vadim.naujavaprjct.exception;

import ru.vadim.naujavaprjct.dto.CustomErrorDto;

public class UserNotFoundError extends CustomErrorDto {

    public UserNotFoundError(Long userId) {
        super(ErrorType.USER_NOT_FOUND, "Пользователь с ID " + userId + " не найден.");
    }
}
