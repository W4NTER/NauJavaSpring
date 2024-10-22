package ru.vadim.naujavaprjct.exception;

import ru.vadim.naujavaprjct.dto.CustomExceptionDto;

public class EntityNotFoundException extends CustomExceptionDto {

    public EntityNotFoundException(String nameOfEntity) {
        super(ExceptionType.ENTITY_NOT_FOUND, String.format("Entity - (%s) not found", nameOfEntity));
    }
}
