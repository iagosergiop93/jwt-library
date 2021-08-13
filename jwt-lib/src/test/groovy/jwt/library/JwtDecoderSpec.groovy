package jwt.library

import io.jsonwebtoken.SignatureAlgorithm
import spock.lang.Specification

import javax.crypto.spec.SecretKeySpec

class JwtDecoderSpec extends Specification {

    JwtConfig jwtConfig;
    JwtGenerator jwtGenerator;
    JwtDecoder jwtDecoder;

    def "setup"() {
        jwtConfig = new JwtConfig();
        jwtConfig.setExpiration(1000 * 60)
        jwtConfig.signatureAlg = SignatureAlgorithm.HS256;
        jwtConfig.buildKey("testaqweeryytvvyvyvytvyrexvnvuyfvyukvvyuvuykyvuyjh");
        jwtGenerator = new JwtGenerator(jwtConfig)
        jwtDecoder = new JwtDecoder(jwtConfig)
    }

    def "token must contain issuer"() {
        setup:
        var metadata = new JwtMetadata()
        metadata.id = "test"
        metadata.issuer = "test"
        var claims = new HashMap()
        claims.put("test", "testclaim")
        metadata.setClaims(claims)
        var token = jwtGenerator.createJwt(metadata)

        when:
        var jwtObj = jwtDecoder.decodeJWT(token)
        println(jwtObj.getBody().get("test"))

        then:
        jwtObj.getBody().get("test") == "testclaim"

    }

}
