package br.com.lanchonete.smashpoint.main;

import br.com.lanchonete.smashpoint.controller.ClienteController;
import br.com.lanchonete.smashpoint.model.DadosCliente;

public class MainCliente extends Main{
    private String nomeCliente;
    private String cpfCliente;

    public void exibirMenuCliente(){
        while (true){
            controller = new ClienteController();
            System.out.println("""
                    
                    [1] Cadastrar Cliente
                    [2] Listar Clientes
                    [3] Buscar Clientes
                    """);
            opcao = read.nextLine();

            if (opcao.equals("1")){
                System.out.println("Digite o nome do cliente:");
                nomeCliente = read.nextLine();

                System.out.println("Digite o CPF do cliente:");
                cpfCliente = read.nextLine();

                controller.adicionar(new DadosCliente(this.nomeCliente,this.cpfCliente));
            } else if (opcao.equals("2")) {
                controller.listar()
                        .forEach(System.out::println);
            } else if (opcao.equals("3")) {
                System.out.println("Qual cliente você deseja buscar?");
                nomeCliente = read.nextLine();
                controller.buscar(nomeCliente)
                        .forEach(System.out::println);
            }else {
                break;
            }
        }
    }
}
