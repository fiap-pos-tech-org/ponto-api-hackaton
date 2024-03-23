package br.com.fiap.hackaton.clockregistryapi.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public BadRequestException(Throwable throwable) {
        super(throwable);
    }

}
