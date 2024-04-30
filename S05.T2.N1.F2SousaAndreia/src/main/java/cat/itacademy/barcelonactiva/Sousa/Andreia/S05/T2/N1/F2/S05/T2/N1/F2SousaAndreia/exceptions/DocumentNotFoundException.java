package cat.itacademy.barcelonactiva.Sousa.Andreia.S05.T2.N1.F2.S05.T2.N1.F2SousaAndreia.exceptions;

public class DocumentNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DocumentNotFoundException(String mensaje) {
        super(mensaje);
    }
}
