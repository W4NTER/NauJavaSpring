package ru.vadim.naujavaprjct.exception;

public class IncorrectInputError extends CustomErrorException {

    public IncorrectInputError() {
        super(ErrorType.INCORRECT_INPUT, "Комманда введена неправильно");
    }
}
