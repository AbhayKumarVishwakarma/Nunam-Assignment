package com.nunam.exception;

public class BatteryException extends RuntimeException{

    public BatteryException(String message) {
        super(message);
    }

    public BatteryException(String message, Throwable cause) {
        super(message, cause);
    }
}
