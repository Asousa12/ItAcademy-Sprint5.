package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F3.S05T2N1F3SousaAndreia.services.Interfaz;


import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F3.S05T2N1F3SousaAndreia.DTO.AuthenticationDTO;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F3.S05T2N1F3SousaAndreia.DTO.AuthenticationResponse;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F3.S05T2N1F3SousaAndreia.DTO.RegisterDTO;
import cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F3.S05T2N1F3SousaAndreia.exception.UserAlreadyExistsException;

public interface AuthServices {

     AuthenticationResponse register (RegisterDTO request);
     AuthenticationResponse authenticate  (AuthenticationDTO request);
}

