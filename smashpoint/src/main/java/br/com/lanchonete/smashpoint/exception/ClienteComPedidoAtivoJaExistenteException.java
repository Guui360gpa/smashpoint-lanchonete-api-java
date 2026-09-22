package br.com.lanchonete.smashpoint.exception;

public class ClienteComPedidoAtivoJaExistenteException extends RuntimeException {
    public ClienteComPedidoAtivoJaExistenteException(String message) {
        super(message);
    }
}
