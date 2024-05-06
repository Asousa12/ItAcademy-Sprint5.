package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F3.S05T2N1F3SousaAndreia.services.Interfaz;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtServices {
    String generateToken(UserDetails userDetails);
    boolean validateToken(String token, UserDetails userDetails);
    String getUserName(String token);




}
