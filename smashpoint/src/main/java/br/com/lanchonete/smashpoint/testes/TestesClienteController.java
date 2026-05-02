package br.com.lanchonete.smashpoint.testes;

import br.com.lanchonete.smashpoint.controller.ClienteController;
import br.com.lanchonete.smashpoint.model.DadosCliente;

public class TestesClienteController {
    public static void main(String[] args) {
        var controller = new ClienteController();

        //controller.adicionar(new DadosCliente(id, mesa, nomeCliente, CPF));
        controller.adicionar(new DadosCliente(1,4,"João","12345678910"));
        controller.adicionar(new DadosCliente(2,9,"Maria","10987654321"));

        System.out.println(controller.listar());
    }
}
