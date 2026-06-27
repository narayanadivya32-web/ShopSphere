package com.example.ShopSphere.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY= "mySecretKeymySecretKeymySecretKeymySecretKey";

    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis()
                                + 1000 * 60 * 60)
                )

        .signWith(
                SignatureAlgorithm.HS256,
                SECRET_KEY
        )
                .compact();
    }

    public String extractEmail(String token){

        Claims claims=Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
                return claims.getSubject();


    }
private Claims extractAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
}

public Date extractExpiration(String token){
        return extractAllClaims(token).getExpiration();
}

public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
}

public boolean isTokenValid(String token, UserDetails userDetails){
        String email=extractEmail(token);

        return email.equals(userDetails.getUsername()) && !isTokenExpired(token);
}


}
