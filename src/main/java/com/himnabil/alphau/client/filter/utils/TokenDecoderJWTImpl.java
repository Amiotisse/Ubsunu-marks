package com.himnabil.alphau.client.filter.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.himnabil.alphau.client.error.InvalidTokenException;

/**
 * @author himna
 * @since 4/16/2017.
 */
public class TokenDecoderJWTImpl implements TokenDecoder<DecodedJWT> {

    private KeyProvider keyProvider ;

    public TokenDecoderJWTImpl(KeyProvider keyProvider) {
        this.keyProvider = keyProvider;
    }

    @Override
    public DecodedJWT decodeAndVerify(String token) {
        try {
            Algorithm algorithm = Algorithm.RSA256(keyProvider.getKey());
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token);
        } catch (JWTVerificationException e){
            e.printStackTrace();
            throw new InvalidTokenException();
        }

    }
}
