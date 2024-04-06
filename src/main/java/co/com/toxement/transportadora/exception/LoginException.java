package co.com.toxement.transportadora.exception;

import io.jsonwebtoken.JwtException;

public class LoginException extends JwtException {

    public LoginException(String e, Throwable cause) {
        super (e, cause);
    }

}
