package br.com.lanchonete.smashpoint.exception;

public class ProdutoInativoException extends RuntimeException {
    public ProdutoInativoException(String message) {
        super(message);
    }
}
