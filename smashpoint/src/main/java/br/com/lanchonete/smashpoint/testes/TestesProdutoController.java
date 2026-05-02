package br.com.lanchonete.smashpoint.testes;

import br.com.lanchonete.smashpoint.controller.ProdutoController;
import br.com.lanchonete.smashpoint.model.DadosProduto;

public class TestesProdutoController {
    public static void main(String[] args) {
        var controller = new ProdutoController();

        //controller.adicionar(new DadosProduto(id, nomeProduto, preço));
        controller.adicionar(new DadosProduto(1,"X-Burguer",24.90));
        controller.adicionar(new DadosProduto(2,"X-Bacon",26.50));
        controller.adicionar(new DadosProduto(3,"X-Cheddar",25.00));

        System.out.println(controller.listar());



    }
}
