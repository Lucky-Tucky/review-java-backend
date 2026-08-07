package review_frontend.java_edition.Config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import review_frontend.java_edition.Model.User;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class AuthUtils {


    @Value("${jwt.key}")
    private String key;

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateJwt(User user){
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("userId",user.getId())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*100))
                .signWith(getSigningKey())
                .compact();
    }
}
