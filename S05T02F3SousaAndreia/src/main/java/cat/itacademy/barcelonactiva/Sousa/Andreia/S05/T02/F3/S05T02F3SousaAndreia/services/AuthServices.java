package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T02.F3.S05T02F3SousaAndreia.services;


import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T02.F3.S05T02F3SousaAndreia.DTO.AuthenticationResponse;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T02.F3.S05T02F3SousaAndreia.DTO.AuthenticationDTO;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T02.F3.S05T02F3SousaAndreia.DTO.RegisterDTO;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T02.F3.S05T02F3SousaAndreia.exception.UsernameAlreadyExistsException;

public interface AuthServices {

     AuthenticationResponse register (RegisterDTO request) throws UsernameAlreadyExistsException;
     AuthenticationResponse authenticate  (AuthenticationDTO request);
}

