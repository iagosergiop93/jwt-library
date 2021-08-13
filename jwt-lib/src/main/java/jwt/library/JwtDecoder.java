package jwt.library;

import io.jsonwebtoken.*;
import jwt.library.exceptions.JwtException;

import java.util.Map;

public class JwtDecoder {

    private JwtConfig jwtConfig;

    public JwtDecoder(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public Jws<Claims> decodeJWT(String jwt) throws JwtException {

        try {
            Jws<Claims> jwtObj = Jwts.parserBuilder()
                    .setSigningKey(jwtConfig.getSIGNING_KEY())
                    .build().parseClaimsJws(jwt);
            return jwtObj;
        } catch (UnsupportedJwtException ex) {
            throw new JwtException(ex.getCause());
        }
    }

    public Map<String, Object> getJwtHeader(String jwt) throws JwtException {

        try {
            Jws<Claims> jwtObj = decodeJWT(jwt);
            Map<String, Object> claims = jwtObj.getHeader();
            return claims;
        } catch (UnsupportedJwtException ex) {
            throw new JwtException(ex.getCause());
        }
    }

    public Map<String, Object> getJwtBody(String jwt) throws JwtException {
        try {
            Jws<Claims> jwtObj = decodeJWT(jwt);
            Map<String, Object> claims = jwtObj.getBody();
            return claims;
        } catch (UnsupportedJwtException ex) {
            throw new JwtException(ex.getCause());
        }

    }

    public boolean verifySignature(String jwt) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(jwtConfig.getSIGNING_KEY())
                    .build().isSigned(jwt);
        } catch (RuntimeException ex) {
            return false;
        }
        return true;
    }

}
