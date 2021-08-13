package jwt.library

import io.jsonwebtoken.SignatureAlgorithm
import spock.lang.Specification

import javax.crypto.spec.SecretKeySpec

class JwtGeneratorSpec extends Specification {

    JwtConfig jwtConfig;
    JwtGenerator jwtGenerator;

    def "setup"() {
        jwtConfig = new JwtConfig();
        jwtConfig.setExpiration(1000 * 60)
        jwtConfig.signatureAlg = SignatureAlgorithm.HS256;
        jwtConfig.buildKey("testaqweeryytvvyvyvytvyrexvnvuyfvyukvvyuvuykyvuyjh");
        jwtGenerator = new JwtGenerator(jwtConfig)
    }

    def "token must contain issuer"() {
        when:
        var metadata = new JwtMetadata()
        metadata.id = "test"
        metadata.issuer = "test"
        var token = jwtGenerator.createJwt(metadata)

        then:
        assert metadata.issuer
    }

    def "throws for metadata without id"() {
        when:
        var metadata = new JwtMetadata()
        metadata.issuer = "test"
        var token = jwtGenerator.createJwt(metadata)

        then:
        thrown(RuntimeException.class)
    }

}
