package br.com.lanchonete.smashpoint.service;

import br.com.lanchonete.smashpoint.model.Produto;
import br.com.lanchonete.smashpoint.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.*;

public class ProdutoService {

    private List<Produto> produtos = new ArrayList<>();
    private Scanner read = new Scanner(System.in);

    @Autowired
    private ProdutoRepository repository;

    public void cadastrarProduto(){
        System.out.println("Digite o nome do produto:");
        var nomeProduto = read.nextLine();

        System.out.println("Digite a categoria:");
        var categoria = read.nextLine();

        System.out.println("Digite o preço do produto:");
        String precoStr = read.nextLine().replace(",",".");
        var precoProduto = new BigDecimal(precoStr);

        criar(new Produto(nomeProduto,categoria,precoProduto));
    }




    private void criar(Produto produto) {
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

    public void buscarProduto(){
        System.out.println("Digite o nome do produto:");
        var nomeProduto = read.nextLine();

        var produtosEncontrados =
                repository.findByNomeContainingIgnoreCase(nomeProduto);

        if (produtosEncontrados.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
        } else {

            System.out.println("\nProdutos encontrados:");

            produtosEncontrados.forEach(produto ->
                    System.out.println(
                            produto.getId()
                                    + " - "
                                    + produto.getNome()
                                    + " | "
                                    + produto.getCategoria()
                                    + " | R$ "
                                    + produto.getPreco()
                    )
            );
        }
    }
}

