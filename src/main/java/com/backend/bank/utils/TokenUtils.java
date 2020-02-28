package com.backend.bank.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;

public class TokenUtils {
    private static final long EXP_TIME= 150000;
    private static final Logger logger = LoggerFactory.getLogger(TokenUtils.class);

    public static String createToken(String username, String secret) {
        String token="";
        long nowMillis = System.currentTimeMillis();

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            token = JWT.create()
                    .withExpiresAt(new Date(nowMillis + EXP_TIME))
                    .withIssuer("auth0")
                    .withClaim("username", username)
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            logger.error("Couldn't create token. Invalid configuration.");
        }

        return token;
    }

    public static boolean verifyToken(String token, String secret) {
        boolean tokenAccepted = false;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            tokenAccepted = true;
        } catch (JWTVerificationException exception) {
            logger.error("Invalid token");
        }

        return tokenAccepted;
    }

    /*Decode and create a new token from the existent token*/
    public static String refreshToken(String token, String secret) {
        return createToken(decodeToken(token), secret);
    }

    /* Decode the token and get the user name in it*/
    private static String decodeToken(String token) {
        String username ="";
        DecodedJWT jwt = JWT.decode(token);
        username = jwt.getClaim("username").asString();

        return username;
    }
}
