package ru.vadim.naujavaprjct.exception;

public class NotValidIdError extends CustomErrorException {

    public NotValidIdError() {
        super(ErrorType.NOT_VALID_ID, "Некорректный формат ID");
    }
}
