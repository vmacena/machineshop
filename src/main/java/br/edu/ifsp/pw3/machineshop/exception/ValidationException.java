package br.edu.ifsp.pw3.machineshop.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}