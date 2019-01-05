package com.samsolutions.logistics.mainlogistics.dto;

import java.io.Serializable;

public class ErrorDTO implements Serializable{
    private String errorMsg;
    private String cause;
    private boolean hasError;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }
}
