package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T02.F3.S05T02F3SousaAndreia.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtServices {
    String generateToken(UserDetails userDetails);
    boolean validateToken(String token, UserDetails userDetails);
    String getUserName(String token);




}
