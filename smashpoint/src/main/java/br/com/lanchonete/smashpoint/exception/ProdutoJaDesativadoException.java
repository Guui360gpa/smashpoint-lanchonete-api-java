package br.com.lanchonete.smashpoint.exception;

public class ProdutoJaDesativadoException extends RuntimeException {
    public ProdutoJaDesativadoException(String message) {
        super(message);
    }
}
