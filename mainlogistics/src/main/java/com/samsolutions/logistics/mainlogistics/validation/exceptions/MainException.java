package com.samsolutions.logistics.mainlogistics.validation.exceptions;

/**
 * Main exception in project, rest exceptions extends from this
 */
public class MainException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String localizedMessage;
    private String localizedCauseMessage;

    @Override
    public String getLocalizedMessage() {
        return localizedMessage;
    }

    public void setLocalizedMessage(String localizedMessage) {
        this.localizedMessage = localizedMessage;
    }

    public String getLocalizedCauseMessage() {
        return localizedCauseMessage;
    }

    public void setLocalizedCauseMessage(String localizedCauseMessage) {
        this.localizedCauseMessage = localizedCauseMessage;
    }
}
