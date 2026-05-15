package br.com.lanchonete.smashpoint.main;

import br.com.lanchonete.smashpoint.controller.ClienteController;
import br.com.lanchonete.smashpoint.controller.Controller;
import br.com.lanchonete.smashpoint.controller.PedidoController;
import br.com.lanchonete.smashpoint.controller.ProdutoController;

import java.util.List;
import java.util.Scanner;

public class Main {

    protected Scanner read = new Scanner(System.in);
    protected String opcao;
    protected Controller controller;

    public void exibirMenuMain(){

        while (true){
            System.out.println("-------------------------");
            System.out.println("========SMASHPOINT=======");
            System.out.println("-------------------------");

            System.out.println("""
                [1] Pedido
                [2] Produto
                [3] Cliente
                """);
            opcao = read.nextLine();

            if(this.opcao.equals("1")){
                MainPedido mainPedido = new MainPedido();
                mainPedido.exibirMenuPedidos();
            } else if (this.opcao.equals("2")) {
                MainProduto mainProduto = new MainProduto();
                mainProduto.exibirMenuProduto();
            } else if (this.opcao.equals("3")) {
                MainCliente mainCliente = new MainCliente();
                mainCliente.exibirMenuCliente();
        }else {
                break;
            }

    }
}
}
