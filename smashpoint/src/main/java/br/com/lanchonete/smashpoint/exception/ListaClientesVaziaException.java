package br.com.lanchonete.smashpoint.exception;

public class ListaClientesVaziaException extends RuntimeException {
    public ListaClientesVaziaException(String message) {
        super(message);
    }
}
