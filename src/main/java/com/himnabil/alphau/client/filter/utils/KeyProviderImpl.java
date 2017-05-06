package com.himnabil.alphau.client.filter.utils;

import com.himnabil.alphau.client.AlphaUFiegnClient;
import com.himnabil.alphau.client.error.ErrorWhenReceivingPublicKeyException;
import org.apache.commons.codec.binary.Base64;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author himna
 * @since 4/16/2017.
 */
public class KeyProviderImpl implements KeyProvider {


    private AlphaUFiegnClient alphaUClient ;

    public KeyProviderImpl(AlphaUFiegnClient alphaUClient) {
        this.alphaUClient = alphaUClient;
    }

    private RSAPublicKey key ;

    private RSAPublicKey getAlphaUKey(){
        String key = alphaUClient.getPublicKey();
        byte[] publicBytes = Base64.decodeBase64(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
        try {
            return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(keySpec);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new ErrorWhenReceivingPublicKeyException(e);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            throw new ErrorWhenReceivingPublicKeyException(e);
        }

    }

    @Override
    public RSAPublicKey getKey() {
        if (key == null)  key = getAlphaUKey() ;
        return key;
    }

    @Override
    public void reset() {
        key = null ;
    }
}
