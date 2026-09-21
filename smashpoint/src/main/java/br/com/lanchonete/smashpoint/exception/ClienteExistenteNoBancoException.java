package br.com.lanchonete.smashpoint.exception;

public class ClienteExistenteNoBancoException extends RuntimeException {
  public ClienteExistenteNoBancoException(String message) {
    super(message);
  }
}
