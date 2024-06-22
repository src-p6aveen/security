package com.example.security.service.jwt;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setSubject(username)
                .claim("TIMEOUT", 4)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }


    public boolean validateUserLoginTimeBreached(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date issuedAt = claims.getIssuedAt();
            if (isTimeBreachedInMin(new Date(), issuedAt)) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    private boolean isTimeBreached(Date date1, Date date2) {
        long timeDifference = date1.getTime() - date2.getTime();
        long timeDifferenceInHours = timeDifference / (60 * 60 * 1000);
        return timeDifferenceInHours > 1;
    }

    private boolean isTimeBreachedInMin(Date date1, Date date2) {
        long timeDifference = date1.getTime() - date2.getTime();
        long timeDifferenceInHours = timeDifference / (5 * 60 * 1000);
        return timeDifferenceInHours > 1;
    }

    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }
}
