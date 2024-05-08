package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T02.F3.S05T02F3SousaAndreia.controllers;

import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T02.F3.S05T02F3SousaAndreia.DTO.AuthenticationDTO;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T02.F3.S05T02F3SousaAndreia.DTO.AuthenticationResponse;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T02.F3.S05T02F3SousaAndreia.DTO.RegisterDTO;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T02.F3.S05T02F3SousaAndreia.exception.UsernameAlreadyExistsException;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T02.F3.S05T02F3SousaAndreia.services.AuthServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private AuthServices authService;

    @PostMapping("/register")
    public ResponseEntity <AuthenticationResponse> register(@RequestBody RegisterDTO request) {
        try {
            return ResponseEntity.ok(authService.register(request));
        } catch (UsernameAlreadyExistsException e) {
            throw new RuntimeException(e);
        }

    }
    @PostMapping("/authenticate")
    public ResponseEntity <AuthenticationResponse> authenticate(@RequestBody AuthenticationDTO request){
        return ResponseEntity.ok(authService.authenticate(request));

    }
}


