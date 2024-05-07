package cat.itacademy.barcelonactiva.Sousa.Andreia.t52.n1.f1.t52.n1.f1SousaAndreia.exception;

public class PlayerAlreadyExistsException extends RuntimeException {

    public PlayerAlreadyExistsException() {
        super("Player already exists");
    }

    public PlayerAlreadyExistsException(String message) {
        super(message);
    }

    public PlayerAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
