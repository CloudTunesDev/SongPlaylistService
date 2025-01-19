package com.cloudtunes.songplaylistserv.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.JwkProviderBuilder;
import com.auth0.jwk.JwkException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.interfaces.RSAPublicKey;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final String AUTH0_DOMAIN = "http://cloudtunes.io";
    private static final String AUTH0_AUDIENCE = "https://cloudtunes.io/api";

    @PostMapping("/verify-token")
    public ResponseEntity<String> verifyToken(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            // Extract the token from the Authorization header
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                return new ResponseEntity<>("Authorization header is missing or invalid", HttpStatus.UNAUTHORIZED);
            }

            String token = authorizationHeader.substring(7); // Remove "Bearer " prefix

            // Get the JWK provider for Auth0
            JwkProvider jwkProvider = new JwkProviderBuilder(AUTH0_DOMAIN)
                    .cached(10, 24, TimeUnit.HOURS) // Cache up to 10 keys for 24 hours
                    .build();

            // Decode the token to get the kid
            DecodedJWT decodedJWT = JWT.decode(token);
            String kid = decodedJWT.getKeyId();

            // Get the public key from the JWK provider
            RSAPublicKey publicKey = (RSAPublicKey) jwkProvider.get(kid).getPublicKey();

            // Verify the token
            Algorithm algorithm = Algorithm.RSA256(publicKey, null);
            algorithm.verify(decodedJWT);

            // Check audience and issuer
            if (!AUTH0_AUDIENCE.equals(decodedJWT.getAudience().get(0)) ||
                    !AUTH0_DOMAIN.equals(decodedJWT.getIssuer())) {
                return new ResponseEntity<>("Invalid token audience or issuer", HttpStatus.UNAUTHORIZED);
            }

            // If valid, return success
            return ResponseEntity.ok("Token is valid!");

        } catch (JwkException e) {
            return new ResponseEntity<>("Failed to get public key: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid token: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
