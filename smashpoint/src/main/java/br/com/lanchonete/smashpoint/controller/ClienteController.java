package br.com.lanchonete.smashpoint.controller;

import br.com.lanchonete.smashpoint.model.Cliente;
import br.com.lanchonete.smashpoint.repository.ClienteRepository;
import java.util.*;

public class ClienteController{

    private List<Cliente> clientes = new ArrayList<>();
    private ClienteRepository repository;

    public ClienteController(ClienteRepository repository) {
        this.repository = repository;

        if (repository == null) {
            System.out.println("Repository veio NULL");
        }
    }

    public void criar(Cliente cliente) {
        try {
            repository.save(cliente);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ler() {
        clientes = repository.findAll();
        clientes.forEach(System.out::println);
    }


}