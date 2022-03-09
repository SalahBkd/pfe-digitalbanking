package org.ybs.coreapi.exceptions;

public class NegativeAmountException extends RuntimeException{
    public NegativeAmountException(String msg) {
        super(msg);
    }
}
