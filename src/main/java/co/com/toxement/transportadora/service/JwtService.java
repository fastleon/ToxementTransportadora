package co.com.toxement.transportadora.service;

import co.com.toxement.transportadora.entity.TransportadoraCredencial;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${security.jwt.token-expiration-minutes}")
    private Long TOKEN_EXPIRATION_MINUTES;
    @Value("${security.jwt.token-key}")
    private String TOKEN_KEY;

    public String generateToken(TransportadoraCredencial credencial, Map<String, Object> extraClaims) {

        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date( issuedAt.getTime() + (TOKEN_EXPIRATION_MINUTES*60*1000) );

        return Jwts.builder()
                //.claims(extraClaims)
                .subject(credencial.getUsuario())
                .issuer("Toxement-API")
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(generateKey())
                .compact();
    }

    private SecretKey generateKey() {
        byte[] secretword = Decoders.BASE64.decode(TOKEN_KEY);
        return Keys.hmacShaKeyFor(secretword);
    }

    public String extractUsername(String token) throws io.jsonwebtoken.JwtException, IllegalArgumentException{
            return extractallClaims(token).getSubject();
    }

    public Claims extractallClaims(String token) throws io.jsonwebtoken.JwtException, IllegalArgumentException{

         return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

}
