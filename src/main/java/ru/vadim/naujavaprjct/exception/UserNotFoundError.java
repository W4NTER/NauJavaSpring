package ru.vadim.naujavaprjct.exception;

public class UserNotFoundError extends CustomErrorException {

    public UserNotFoundError(Long userId) {
        super(ErrorType.USER_NOT_FOUND, "Пользователь с ID " + userId + " не найден.");
    }
}
