package com.edutech.progressive.exception;

public class TeamCricketerLimitExceededException extends RuntimeException {
    public TeamCricketerLimitExceededException(String message) {
        super(message);
    }
}