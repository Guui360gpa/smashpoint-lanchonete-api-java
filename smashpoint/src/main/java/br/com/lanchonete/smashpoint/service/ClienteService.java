package br.com.lanchonete.smashpoint.service;

import br.com.lanchonete.smashpoint.model.Cliente;
import br.com.lanchonete.smashpoint.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClienteService{

    private List<Cliente> clientes = new ArrayList<>();
    private Scanner read = new Scanner(System.in);

    @Autowired
    private ClienteRepository repository;

    public void cadastrar(){
        System.out.println("Digite o nome do cliente:");
        var nomeCliente = read.nextLine();

        criar(new Cliente(nomeCliente));
    }


    private void criar(Cliente cliente) {
        try {
            repository.save(cliente);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listar() {
        clientes = repository.findAll();
        clientes.forEach(System.out::println);
    }

    public void buscar(){
        System.out.println("Digite o nome do cliente:");
        var nomeCliente = read.nextLine();

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
    }

}