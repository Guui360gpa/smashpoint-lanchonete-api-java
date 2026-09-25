package br.com.lanchonete.smashpoint.exception;

public class ItemPedidoNaoEncontradoException extends RuntimeException {
    public ItemPedidoNaoEncontradoException(String message) {
        super(message);
    }
}
