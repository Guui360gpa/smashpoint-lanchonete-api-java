package br.com.lanchonete.smashpoint.exception;

public class ListaProdutosVaziaException extends RuntimeException {
    public ListaProdutosVaziaException(String message) {
        super(message);
    }
}
