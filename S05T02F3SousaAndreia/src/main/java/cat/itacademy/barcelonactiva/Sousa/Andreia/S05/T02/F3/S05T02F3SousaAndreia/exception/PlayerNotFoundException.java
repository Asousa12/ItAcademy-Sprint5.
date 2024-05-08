package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T02.F3.S05T02F3SousaAndreia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PlayerNotFoundException extends ResponseStatusException {

    public PlayerNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Player not found");
    }

    public PlayerNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}