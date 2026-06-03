package br.com.lanchonete.smashpoint.main;

import br.com.lanchonete.smashpoint.controller.ProdutoController;
import br.com.lanchonete.smashpoint.model.Produto;
import br.com.lanchonete.smashpoint.repository.ProdutoRepository;

import java.math.BigDecimal;

public class MainProduto extends Main{

    //Declaração de variaveis
    private String nomeProduto;
    private BigDecimal precoProduto;
    private String categoria;
    private ProdutoRepository repository;
    private ProdutoController controller;

    //Construtor
    public MainProduto(ProdutoRepository repository) {
        super(repository);
        this.repository = repository;
        System.out.println("Repository recebido: " + repository);
        this.controller = new ProdutoController(repository);
    }



    //Métods
    protected void exibirMenuProduto(){
        while (true){
            System.out.println("""
                
                [1] Novo Produto
                [2] Ver Produtos
                [3] Buscar Produto
                """);
            opcao = read.nextLine();

            if (opcao.equals("1")){
                System.out.println("Digite o nome do produto:");
                nomeProduto = read.nextLine();

                System.out.println("Digite a categoria:");
                categoria = read.nextLine();

                System.out.println("Digite o preço do produto:");
                String precoStr = read.nextLine().replace(",",".");
                precoProduto = new BigDecimal(precoStr);

                controller.criar(new Produto(nomeProduto,categoria,precoProduto));
            } else if (opcao.equals("2")) {
                controller.ler();
            } else if (opcao.equals("3")) {
                System.out.println("Digite o nome do produto:");
                nomeProduto = read.nextLine();

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
            }else {
                break;
            }
        }

    }
}
