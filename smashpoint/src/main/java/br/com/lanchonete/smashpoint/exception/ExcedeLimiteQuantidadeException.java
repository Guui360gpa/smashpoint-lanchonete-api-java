package br.com.lanchonete.smashpoint.exception;

public class ExcedeLimiteQuantidadeException extends RuntimeException {
    public ExcedeLimiteQuantidadeException(String message) {
        super(message);
    }
}
