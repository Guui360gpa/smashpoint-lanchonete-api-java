package br.com.lanchonete.smashpoint.main;

import br.com.lanchonete.smashpoint.controller.ProdutoController;
import br.com.lanchonete.smashpoint.model.DadosProduto;

public class MainProduto extends Main{
    private String nomeProduto;
    private double precoProduto;

    protected void exibirMenuProduto(){
        while (true){
            controller = new ProdutoController();
            System.out.println("""
                
                [1] Novo Produto
                [2] Ver Produtos
                [3] Buscar Produto
                """);
            opcao = read.nextLine();

            if (opcao.equals("1")){
                System.out.println("Digite o nome do produto:");
                nomeProduto = read.nextLine();

                System.out.println("Digite o preço do produto:");
                precoProduto = read.nextDouble();

                controller.adicionar(new DadosProduto(this.nomeProduto,this.precoProduto));
            } else if (opcao.equals("2")) {
                controller.listar()
                        .forEach(System.out::println);
            } else if (opcao.equals("3")) {
                System.out.println("Qual produto você deseja buscar?");
                nomeProduto = read.nextLine();
                controller.buscar(nomeProduto)
                        .forEach(System.out::println);
            }else {
                break;
            }
        }

    }
}
