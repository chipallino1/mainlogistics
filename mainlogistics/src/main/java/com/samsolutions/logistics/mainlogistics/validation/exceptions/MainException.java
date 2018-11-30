package com.samsolutions.logistics.mainlogistics.validation.exceptions;

/**
 * Main exception in project, rest exceptions extends from this
 */
public class MainException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String localizedMessage;
    private Throwable cause;

    @Override
    public String getMessage() {
        return localizedMessage;
    }
    public String getStringCause(){
        return getCause().toString();
    }
}
