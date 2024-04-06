package co.com.toxement.transportadora.exception;

import io.jsonwebtoken.JwtException;

public class TokenException extends JwtException {
    public TokenException (String e, Throwable cause) {
        super (e, cause);
    }
}
