package org.ybs.coreapi.exceptions;

public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(String msg) {
        super(msg);
    }
}
