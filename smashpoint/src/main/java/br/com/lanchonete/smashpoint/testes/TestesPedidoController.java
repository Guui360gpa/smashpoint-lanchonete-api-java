package br.com.lanchonete.smashpoint.testes;

import br.com.lanchonete.smashpoint.controller.PedidoController;
import br.com.lanchonete.smashpoint.model.DadosPedido;

public class TestesPedidoController {
    public static void main(String[] args) {
        PedidoController controller = new PedidoController();

        //controller.adicionar(new DadosPedido(id, title, quantidade, preço));
        controller.adicionar(new DadosPedido (1,"X-Tudo",3,33.90));
        controller.adicionar(new DadosPedido(2,"Coca-cola",2,6.50));
    }
}
