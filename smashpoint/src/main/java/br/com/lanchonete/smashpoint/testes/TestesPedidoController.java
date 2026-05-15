package br.com.lanchonete.smashpoint.testes;

import br.com.lanchonete.smashpoint.controller.PedidoController;
import br.com.lanchonete.smashpoint.controller.ProdutoController;
import br.com.lanchonete.smashpoint.model.DadosPedido;

public class TestesPedidoController {
    public static void main(String[] args) {
        PedidoController controller = new PedidoController();
        ProdutoController produtoController = new ProdutoController();
        //controller.adicionar(new DadosPedido(id, title, quantidade, preço));
//        controller.adicionar(new DadosPedido (produtoController.buscar(1),3));
//        controller.adicionar(new DadosPedido(produtoController.buscar(2),2));


    }
}
