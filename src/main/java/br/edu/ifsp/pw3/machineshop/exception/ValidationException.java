package br.edu.ifsp.pw3.machineshop.exception;

import java.util.regex.Pattern;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}