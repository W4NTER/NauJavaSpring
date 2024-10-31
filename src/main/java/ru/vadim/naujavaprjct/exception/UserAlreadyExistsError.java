package ru.vadim.naujavaprjct.exception;

public class UserAlreadyExistsError extends CustomErrorException {

    public UserAlreadyExistsError(Long userId) {
        super(ErrorType.USER_ALREADY_EXISTS, String.format("Пользователь с таким ID = %s, уже существует", userId));
    }
}
