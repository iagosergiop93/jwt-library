package jwt.library;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

import java.util.Date;

public class JwtGenerator {

    private JwtConfig jwtConfig;

    public JwtGenerator(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String createJwt(JwtMetadata metadata) {

        validateMetadata(metadata);

        long nowMillis = System.currentTimeMillis();
        JwtBuilder builder = Jwts.builder()
                .setId(metadata.getId())
                .setSubject(metadata.getSubject())
                .setIssuer(metadata.getIssuer())
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(new Date(nowMillis + jwtConfig.getExpiration()))
                .signWith(jwtConfig.getSIGNING_KEY(), jwtConfig.getSignatureAlg());

        if(metadata.getClaims() != null && !metadata.getClaims().isEmpty()) {
            metadata.getClaims().forEach((key,value) -> {
                builder.claim(key,value);
            });
        }

        return builder.compact();
    }

    private void validateMetadata(JwtMetadata metadata) {
        if(metadata == null || metadata.getId() == null || metadata.getIssuer() == null) {
            throw new RuntimeException("At least id, subject and issuer need to be specified in the metadata");
        }
    }

}
