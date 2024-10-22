package ru.vadim.naujavaprjct.exception;

import ru.vadim.naujavaprjct.dto.CustomErrorDto;

public class NotValidIdError extends CustomErrorDto {

    public NotValidIdError() {
        super(ErrorType.NOT_VALID_ID, "Некорректный формат ID");
    }
}
