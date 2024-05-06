package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F3.S05T2N1F3SousaAndreia.exception;

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
