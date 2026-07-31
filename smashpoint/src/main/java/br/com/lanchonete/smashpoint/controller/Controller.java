package br.com.lanchonete.smashpoint.controller;

import br.com.lanchonete.smashpoint.repository.ClienteRepository;
import br.com.lanchonete.smashpoint.repository.PedidoRepository;
import br.com.lanchonete.smashpoint.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;


public class Controller {

    private Scanner read = new Scanner(System.in);
    private int opcao;

    @Autowired
    private ClienteController clienteController;

    @Autowired
    private PedidoController pedidoController;

    @Autowired
    private ProdutoController produtoController;

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
            opcao = read.nextInt();

            switch (opcao){
                case 1:
                    pedidoController.realizarPedido();
                    break;
                case 2:
                    produtoController.exibirMenuProduto();
                    break;
                case 3:
                    clienteController.exibirMenuCliente();
            }

    }
}
}
