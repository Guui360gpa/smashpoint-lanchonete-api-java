package br.com.lanchonete.smashpoint.controller;

import java.util.List;

public interface Controller<T> {

    List<T> listar();

    T adicionar(T obj);

    T buscar(int id);
}