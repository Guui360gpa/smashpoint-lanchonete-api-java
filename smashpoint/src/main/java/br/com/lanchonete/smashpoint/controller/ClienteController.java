package br.com.lanchonete.smashpoint.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class ClienteController{

    private Scanner read = new Scanner(System.in);
    private int opcao;


    @Autowired
    private ClienteService clienteService;

    public void exibirMenuCliente(){
        while (true){

            System.out.println("""
                    
                    [1] Cadastrar Cliente
                    [2] Listar Clientes
                    [3] Buscar Clientes
                    """);
            opcao = read.nextInt();

            switch (opcao){
                case 1:
                    clienteService.cadastrar();
                    break;
                case 2:
                    clienteService.listar();
                    break;
                case 3:
                    clienteService.buscar();
                    break;
                default:
                    break;
            }
        }
    }
}
