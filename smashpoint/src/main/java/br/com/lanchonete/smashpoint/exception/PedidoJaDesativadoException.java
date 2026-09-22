package br.com.lanchonete.smashpoint.exception;

public class PedidoJaDesativadoException extends RuntimeException {
    public PedidoJaDesativadoException(String message) {
        super(message);
    }
}
