package br.com.lanchonete.smashpoint.controller;

import br.com.lanchonete.smashpoint.model.Produto;
import br.com.lanchonete.smashpoint.repository.ProdutoRepository;
import java.util.*;

public class ProdutoController {

    private List<Produto> produtos = new ArrayList<>();
    private ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;

        if (repository == null) {
            System.out.println("Repository veio NULL");
        }
    }

    public void criar(Produto produto) {
        try {
            repository.save(produto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ler() {
        produtos = repository.findAll();
        produtos.stream()
                .sorted(Comparator.comparing(Produto::getCategoria))
                .forEach(System.out::println);
    }
}

