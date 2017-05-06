package com.amiotisse.ubsunu.marks;

/**
 * @author himna
 * @since 4/8/2017.
 */
public class ApiError {

    private String errorCode ;
    private String errorDescription ;

    public ApiError(String errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiError apiError = (ApiError) o;

        if (errorCode != null ? !errorCode.equals(apiError.errorCode) : apiError.errorCode != null) return false;
        return errorDescription != null ? errorDescription.equals(apiError.errorDescription) : apiError.errorDescription == null;
    }

    @Override
    public int hashCode() {
        int result = errorCode != null ? errorCode.hashCode() : 0;
        result = 31 * result + (errorDescription != null ? errorDescription.hashCode() : 0);
        return result;
    }
}
