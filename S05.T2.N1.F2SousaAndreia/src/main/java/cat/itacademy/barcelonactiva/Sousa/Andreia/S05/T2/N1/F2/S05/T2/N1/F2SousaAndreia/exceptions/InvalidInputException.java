package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.exceptions;

public class InvalidInputException extends Throwable {
    public InvalidInputException(String datosDeJuegoInvalidos) {
        super(datosDeJuegoInvalidos);
    }
}
