package br.com.lanchonete.smashpoint.controller;

import java.util.List;
import java.util.Map;

public interface Controller<T> {

    List<T> listar();

    T adicionar(T obj);

    List<T> buscar(String nome);
}