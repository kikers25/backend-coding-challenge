package com.engage.expense.service;

public class AppException extends RuntimeException {

    public AppException(String error) {
        super(error);
    }
}
