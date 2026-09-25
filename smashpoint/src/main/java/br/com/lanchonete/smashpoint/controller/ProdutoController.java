package br.com.lanchonete.smashpoint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class ProdutoController{

    private int opcao;
    private Scanner read = new Scanner(System.in);

    @Autowired
    private ProdutoService service;

    protected void exibirMenuProduto(){
        while (true){
            System.out.println("""
                
                [1] Novo Produto
                [2] Ver Produtos
                [3] Buscar Produto
                """);
            opcao = read.nextInt();

            switch (opcao){
                case 1:
                    service.cadastrarProduto();
                    break;
                case 2:
                    service.ler();
                    break;
                case 3:
                    service.buscarProduto();
                    break;

                default:
                    break;
            }
        }

    }
}
