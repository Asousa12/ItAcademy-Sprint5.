package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F3.S05T2N1F3SousaAndreia.configuration;

import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F3.S05T2N1F3SousaAndreia.services.Interfaz.JwtServices;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.security.Key;


@Service
@RequiredArgsConstructor
public class JwtServicesImp implements JwtServices {

    private static final String SECRET_KEY = "7e95c3ad956628a81bd348db3d1e87d78fb842d9ff170c8c3aa148f8edca7e93";
    public String generateToken (UserDetails userDetails){
        return generateToken (new HashMap<>(),userDetails);
    }

    public String generateToken(Map<String, Object> extracClaims, UserDetails userDetails){
        return Jwts.builder().setClaims(extracClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 *60 *24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUserName (String token) {
        return getClaim (token, Claims::getSubject);
    }

    public <T> T getClaim (String token, Function<Claims,T> claimsResolver){
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaims (String token) {
        return Jwts.parser().verifyWith((SecretKey) getSignInKey()).build().parseSignedClaims(token).getPayload();
    }

    private Key getSignInKey (){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public boolean validateToken (String token, UserDetails userDetails){
        final String username =  getUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    private boolean isTokenExpired (String token){
        return getExpiration(token).before(new Date());
    }
    private Date getExpiration(String token){
        return getClaim(token, Claims::getExpiration);
    }
}

