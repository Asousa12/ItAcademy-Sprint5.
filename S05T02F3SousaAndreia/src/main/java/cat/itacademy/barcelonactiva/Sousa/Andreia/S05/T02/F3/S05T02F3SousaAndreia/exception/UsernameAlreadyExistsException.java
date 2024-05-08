package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T02.F3.S05T02F3SousaAndreia.exception;

public class UsernameAlreadyExistsException extends Throwable {
    public UsernameAlreadyExistsException(String usernameAlreadyExists) {

        super(usernameAlreadyExists);
    }
}
