package io.skai.reservation.exception;

public class EntityDoesNotExistsException extends RuntimeException {
    public EntityDoesNotExistsException(String message) {
        super(message);
    }
}