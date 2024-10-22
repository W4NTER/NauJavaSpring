package ru.vadim.naujavaprjct.exception;

public class UserNotFoundError extends RuntimeException {
    public UserNotFoundError(Exception e) {
        super(e);
    }
}
