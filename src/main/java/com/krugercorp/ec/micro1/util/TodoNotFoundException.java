package com.krugercorp.ec.micro1.util;

public class TodoNotFoundException extends RuntimeException {
    public TodoNotFoundException(String msg){
        super(msg);
    }
}
