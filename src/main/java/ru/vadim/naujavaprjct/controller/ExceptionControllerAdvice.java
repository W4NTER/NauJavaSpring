package ru.vadim.naujavaprjct.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.vadim.naujavaprjct.dto.CustomExceptionDTO;
import ru.vadim.naujavaprjct.exception.CustomException;
import ru.vadim.naujavaprjct.exception.EntityAlreadyExistsException;
import ru.vadim.naujavaprjct.exception.EntityNotFoundException;
import ru.vadim.naujavaprjct.exception.UsernameAlreadyInUseException;
import ru.vadim.naujavaprjct.exception.UserNotFoundException;

@ControllerAdvice
public class ExceptionControllerAdvice {
    private static final Logger LOGGER = LogManager.getLogger();

    @ExceptionHandler(java.lang.Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public CustomExceptionDTO exception(java.lang.Exception e) {
        LOGGER.info(e.getMessage());
        return new CustomExceptionDTO(e.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomExceptionDTO exception(ResourceNotFoundException e) {
        LOGGER.info(e.getMessage());
        return new CustomExceptionDTO(e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomExceptionDTO userNotFound(UserNotFoundException e) {
        LOGGER.info(String.format("Exception type - (%s), Message - %s", e.getErrorType(), e.getMessage()));
        return new CustomExceptionDTO(e.getErrorType(), "Пользователь не найден");
    }

    @ExceptionHandler(UsernameAlreadyInUseException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public CustomExceptionDTO conflictUser(UsernameAlreadyInUseException e) {
        LOGGER.info(String.format("Exception type - (%s), Message - %s", e.getErrorType(), e.getMessage()));
        return new CustomExceptionDTO(e.getErrorType(), "Username уже испльзуется другим пользователем");
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    public CustomExceptionDTO entityAlreadyExists(EntityAlreadyExistsException e) {
        LOGGER.info(String.format("Exception type - (%s), Message - %s", e.getErrorType(), e.getMessage()));
        return new CustomExceptionDTO(e.getErrorType(), e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomExceptionDTO conflictUser(EntityNotFoundException e) {
        LOGGER.info(String.format("Exception type - (%s), Message - %s", e.getErrorType(), e.getMessage()));
        return new CustomExceptionDTO(e.getErrorType(), e.getMessage());
    }

}
