package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T02.F3.S05T02F3SousaAndreia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class GameNotFoundException extends ResponseStatusException {

    public GameNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Game not found");
    }

    public GameNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}