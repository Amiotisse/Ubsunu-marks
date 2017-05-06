package com.amiotisse.ubsunu.marks.model.dto;

import com.amiotisse.ubsunu.marks.model.builder.UserTokenBuilder;
import com.amiotisse.ubsunu.marks.model.UserToken;
import com.amiotisse.ubsunu.marks.model.UserType;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * @author himna
 * @since 4/17/2017.
 */
@Component
public class UserTokenDTO implements Function<DecodedJWT, UserToken> {
    @Override
    public UserToken apply(DecodedJWT decodedJWT) {
        return new UserTokenBuilder()
                .setId(decodedJWT.getClaim("id").asString())
                .setUserName(decodedJWT.getClaim("user_name").asString())
                .setUserType( UserType.fromString.get(decodedJWT.getClaim("userType").asString()))
                .build();
    }
}
