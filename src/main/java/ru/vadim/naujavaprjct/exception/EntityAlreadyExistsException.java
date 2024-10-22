package ru.vadim.naujavaprjct.exception;

import ru.vadim.naujavaprjct.dto.CustomExceptionDto;

public class EntityAlreadyExistsException extends CustomExceptionDto {

    public EntityAlreadyExistsException(String nameOfEntity) {
        super(ExceptionType.ENTITY_ALREADY_EXISTS, String.format("%s already exists", nameOfEntity));
    }
}
