package com.himnabil.alphau.client.filter.utils;

/**
 * @author himna
 * @since 4/16/2017.
 */
public interface TokenDecoder<C> {
    C decodeAndVerify(String token);
}
