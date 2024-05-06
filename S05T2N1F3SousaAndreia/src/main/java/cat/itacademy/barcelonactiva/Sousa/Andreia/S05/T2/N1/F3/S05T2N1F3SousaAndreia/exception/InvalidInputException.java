package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F3.S05T2N1F3SousaAndreia.exception;

public class InvalidInputException extends Throwable {
    public InvalidInputException(String datosDeJuegoInvalidos) {
        super(datosDeJuegoInvalidos);
    }
}
