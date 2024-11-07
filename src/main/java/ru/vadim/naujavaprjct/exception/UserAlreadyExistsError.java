package ru.vadim.naujavaprjct.exception;

public class UserAlreadyExistsError extends CustomErrorException {

    public UserAlreadyExistsError(String username) {
        super(ErrorType.USER_ALREADY_EXISTS, String.format("Пользователь с таким username = %s, уже существует", username));
    }
}
