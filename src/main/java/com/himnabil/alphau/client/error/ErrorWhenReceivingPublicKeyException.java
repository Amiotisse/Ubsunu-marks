package com.himnabil.alphau.client.error;

import java.security.GeneralSecurityException;

/**
 * @author himna
 * @since 4/17/2017.
 */
public class ErrorWhenReceivingPublicKeyException extends RuntimeException {
    public ErrorWhenReceivingPublicKeyException(GeneralSecurityException e) {
    }
}
