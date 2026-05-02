package br.com.lanchonete.smashpoint.service;

public interface IConversorDados {

    <T> T obterDados(String json, Class<T> classe);

}
