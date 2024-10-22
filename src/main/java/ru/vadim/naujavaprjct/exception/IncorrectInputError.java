package ru.vadim.naujavaprjct.exception;

import ru.vadim.naujavaprjct.dto.CustomErrorDto;

public class IncorrectInputError extends CustomErrorDto {

    public IncorrectInputError() {
        super(ErrorType.INCORRECT_INPUT, "Комманда введена неправильно");
    }
}
