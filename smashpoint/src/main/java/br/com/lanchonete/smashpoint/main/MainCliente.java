package br.com.lanchonete.smashpoint.main;

import br.com.lanchonete.smashpoint.controller.ClienteController;
import br.com.lanchonete.smashpoint.model.Cliente;
import br.com.lanchonete.smashpoint.repository.ClienteRepository;

public class MainCliente extends Main{

    //Declaração de variaveis
    private String nomeCliente;
    private ClienteRepository repository;
    private ClienteController controller;

    //Construtor
    public MainCliente(ClienteRepository repositoryCliente) {
        super(repositoryCliente);
        this.repository = repositoryCliente;
        this.controller = new ClienteController(repositoryCliente);
    }


    //Métods
    public void exibirMenuCliente(){
        while (true){

            System.out.println("""
                    
                    [1] Cadastrar Cliente
                    [2] Listar Clientes
                    [3] Buscar Clientes
                    """);
            opcao = read.nextLine();

            if (opcao.equals("1")){
                System.out.println("Digite o nome do cliente:");
                nomeCliente = read.nextLine();

                controller.criar(new Cliente(this.nomeCliente));
            } else if (opcao.equals("2")) {
                controller.ler();
            } else if (opcao.equals("3")) {
                System.out.println(repository);
                System.out.println(repositoryCliente);
                System.out.println("Digite o nome do cliente:");
                nomeCliente = read.nextLine();

                var clientesEncontrados =
                        repository.findByNomeContainingIgnoreCase(nomeCliente);

                if (clientesEncontrados.isEmpty()) {
                    System.out.println("Nenhum cliente encontrado.");
                } else {

                    System.out.println("\nClientes encontrados:");

                    clientesEncontrados.forEach(cliente ->
                            System.out.println(
                                    cliente.getId()
                                            + " - "
                                            + cliente.getNome()
                            )
                    );
                }
            }else {
                break;
            }
        }
    }
}
