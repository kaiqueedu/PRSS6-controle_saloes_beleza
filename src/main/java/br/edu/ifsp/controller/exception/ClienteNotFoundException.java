package br.edu.ifsp.controller.exception;

public class ClienteNotFoundException extends RuntimeException {

    public ClienteNotFoundException(){super();}

    public ClienteNotFoundException(final String message) {
        super(message);
    }

}
