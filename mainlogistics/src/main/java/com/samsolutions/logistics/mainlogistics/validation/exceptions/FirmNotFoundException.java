package com.samsolutions.logistics.mainlogistics.validation.exceptions;

/**
 * Exception class. When firm
 */
public class FirmNotFoundException extends MainException {

    private static final long serialVersionUID = 1L;

    public FirmNotFoundException() {
    }

    public FirmNotFoundException(String localizedMessage,String localizedCauseString) {
        super.setLocalizedMessage(localizedMessage);
        super.setLocalizedCauseMessage(localizedCauseString);
    }
}
