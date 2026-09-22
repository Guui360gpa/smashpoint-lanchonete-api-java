package br.com.lanchonete.smashpoint.exception;

public class ListaPedidoVaziaException extends RuntimeException {
    public ListaPedidoVaziaException(String message) {
        super(message);
    }
}
