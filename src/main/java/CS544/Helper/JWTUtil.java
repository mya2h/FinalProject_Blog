package CS544.Helper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JWTUtil {
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 24 hours

    public String generateToken(String username) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static class LoginRequest {
        private String userName;
        private String password;

        // Constructors, getters, and setters

        public String getUserName() {
            return userName;
        }

        public void setUserName(String username) {
            this.userName = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
