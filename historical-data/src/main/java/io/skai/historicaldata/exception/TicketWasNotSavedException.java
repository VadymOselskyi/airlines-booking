package io.skai.historicaldata.exception;

public class TicketWasNotSavedException extends RuntimeException {
    public TicketWasNotSavedException(String message) {
        super(message);
    }
}