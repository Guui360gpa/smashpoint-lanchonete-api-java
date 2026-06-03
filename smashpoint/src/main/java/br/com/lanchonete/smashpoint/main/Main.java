package br.com.lanchonete.smashpoint.main;

import br.com.lanchonete.smashpoint.repository.ClienteRepository;
import br.com.lanchonete.smashpoint.repository.PedidoRepository;
import br.com.lanchonete.smashpoint.repository.ProdutoRepository;

import java.util.Scanner;

public class Main {
    //Declaração de variaveis
    protected Scanner read = new Scanner(System.in);
    protected String opcao;
    protected ProdutoRepository repositoryProduto;
    protected ClienteRepository repositoryCliente;
    protected PedidoRepository repositoryPedido;

    //Construtores
    public Main(ProdutoRepository repositoryProduto, ClienteRepository repositoryCliente, PedidoRepository repositoryPedido){
        this.repositoryProduto = repositoryProduto;
        this.repositoryCliente = repositoryCliente;
        this.repositoryPedido = repositoryPedido;
    }
    public Main(ProdutoRepository repositoryProduto) {
        this.repositoryProduto = repositoryProduto;
    }

    public Main(ClienteRepository repositoryCliente){
        this.repositoryCliente = repositoryCliente;
    }

    public Main(PedidoRepository repositoryPedido){
        this.repositoryPedido = repositoryPedido;
    }

    //Métodos
    public void exibirMenuMain(){

        while (true){
            System.out.println("-------------------------");
            System.out.println("========SMASHPOINT=======");
            System.out.println("-------------------------");

            System.out.println("""
                [1] Novo Pedido
                [2] Produto
                [3] Cliente
                """);
            opcao = read.nextLine();

            if(this.opcao.equals("1")){
                MainPedido mainPedido = new MainPedido(repositoryProduto,repositoryCliente,repositoryPedido);
                mainPedido.exibirMenuPedidos();
            } else if (this.opcao.equals("2")) {
                MainProduto mainProduto = new MainProduto(repositoryProduto);
                mainProduto.exibirMenuProduto();
            } else if (this.opcao.equals("3")) {
                MainCliente mainCliente = new MainCliente(repositoryCliente);
                mainCliente.exibirMenuCliente();
        }else {
                break;
            }

    }
}
}
