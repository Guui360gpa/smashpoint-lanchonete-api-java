package br.com.lanchonete.smashpoint.main;

import br.com.lanchonete.smashpoint.controller.PedidoController;
import br.com.lanchonete.smashpoint.controller.ProdutoController;
import br.com.lanchonete.smashpoint.model.DadosPedido;
import br.com.lanchonete.smashpoint.model.DadosProduto;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MainPedido extends Main{

    private AtomicInteger contador = new AtomicInteger(1);
    private int idProduto;
    private int quantidade;
    private double preco;
    private List<DadosPedido> produtosPedidos = new ArrayList<>();

    public void exibirMenuPedidos(){
        while (true){
            PedidoController controller = new PedidoController();
            System.out.println("""
                    
                    [1] Novo Pedido
                    [2] Listar Pedidos
                    """);
            opcao = read.nextLine();

            if (opcao.equals("1")){
                ProdutoController produtoController = new ProdutoController();
                while (true){
                    List<DadosProduto> produtos = produtoController.listar();

                    for (int i = 0; i < produtos.size(); i++) {
                        System.out.println("[" + (i + 1) + "] " + produtos.get(i));
                    }
                    System.out.println("999 para");

                    System.out.println("Qual produto você deseja adicionar?");
                    idProduto = read.nextInt();

                    if (idProduto == 999){
                        break;
                    }

                    System.out.println("Quantidade:");
                    quantidade = read.nextInt();

                    produtosPedidos.add(new DadosPedido(produtoController.buscar(idProduto),quantidade));


                    controller.adicionar(produtosPedidos);
                }
            } else if (opcao.equals("2")) {
                controller.listar()
                        .forEach(System.out::println);
            }else {
                break;
            }
        }
    }
}
