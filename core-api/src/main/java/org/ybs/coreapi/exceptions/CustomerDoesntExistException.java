package org.ybs.coreapi.exceptions;

public class CustomerDoesntExistException extends RuntimeException{
    public CustomerDoesntExistException(String msg) {
        super(msg);
    }
}
