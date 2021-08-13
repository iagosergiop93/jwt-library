package jwt.library.exceptions;

public class JwtException extends RuntimeException {

    public JwtException(String msg) {
        super(msg);
    }

    public JwtException(Throwable th) {
        super(th);
    }
}
