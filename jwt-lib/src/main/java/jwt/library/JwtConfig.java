package jwt.library;

import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;

public class JwtConfig {

    private int expiration = 24 * 60 * 60 * 1000;
    private SignatureAlgorithm signatureAlg = SignatureAlgorithm.HS256;
    private Key SIGNING_KEY;

    public JwtConfig() {}

    public JwtConfig(int expiration, SignatureAlgorithm signatureAlg, Key SIGNING_KEY) {
        this.expiration = expiration;
        this.signatureAlg = signatureAlg;
        this.SIGNING_KEY = SIGNING_KEY;
    }

    public Key getSIGNING_KEY() {
        return SIGNING_KEY;
    }

    public void buildKey(String secret) {
        byte[] secretBytes = secret.getBytes(StandardCharsets.UTF_8);
        SignatureAlgorithm algorithm = this.signatureAlg != null ? this.signatureAlg : SignatureAlgorithm.HS256;
        this.SIGNING_KEY = new SecretKeySpec(secretBytes, algorithm.getJcaName());
    }

    public int getExpiration() {
        return expiration;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }

    public SignatureAlgorithm getSignatureAlg() {
        return signatureAlg;
    }

    public String getSignatureAlgStr() {
        return signatureAlg.getJcaName();
    }

    public void setSignatureAlg(SignatureAlgorithm signatureAlg) {
        this.signatureAlg = signatureAlg;
    }
}
