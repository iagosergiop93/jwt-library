package jwt.library;

import java.util.Map;

public class JwtMetadata {

    private String id;
    private String issuer;
    private String subject;
    private Map<String, Object> claims;

    public JwtMetadata() {}

    public JwtMetadata(String id, String issuer, String subject, Map<String, Object> claims) {
        this.id = id;
        this.issuer = issuer;
        this.subject = subject;
        this.claims = claims;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Map<String, Object> getClaims() {
        return claims;
    }

    public void setClaims(Map<String, Object> claims) {
        this.claims = claims;
    }
}
