package cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PlayerNotFoundException extends ResponseStatusException {

    public PlayerNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}