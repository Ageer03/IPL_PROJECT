package com.edutech.progressive.exception;

public class TeamDoesNotExistException extends RuntimeException {
    public TeamDoesNotExistException(String message) {
        super(message);
    }
}