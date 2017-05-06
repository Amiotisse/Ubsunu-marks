package com.himnabil.alphau.client.filter.utils;

import java.security.interfaces.RSAPublicKey;

/**
 * @author himna
 * @since 4/16/2017.
 */
public interface KeyProvider {

    RSAPublicKey getKey();
    void reset();
}
