package br.com.lanchonete.smashpoint.exception;

public class ProdutoExistenteNoBancoException extends RuntimeException {
    public ProdutoExistenteNoBancoException(String message) {
        super(message);
    }
}
