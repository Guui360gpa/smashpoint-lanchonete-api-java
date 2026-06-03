package br.com.lanchonete.smashpoint.main;

import br.com.lanchonete.smashpoint.controller.PedidoController;
import br.com.lanchonete.smashpoint.model.*;
import br.com.lanchonete.smashpoint.repository.ClienteRepository;
import br.com.lanchonete.smashpoint.repository.PedidoRepository;
import br.com.lanchonete.smashpoint.repository.ProdutoRepository;
import java.util.ArrayList;
import java.util.List;

public class MainPedido extends Main{

    //Declaração de variaveis
    private PedidoController controller;

    //Construtor
    public MainPedido(
            ProdutoRepository repositoryProduto,
            ClienteRepository repositoryCliente,
            PedidoRepository repositoryPedido) {

        super(repositoryProduto,
                repositoryCliente,
                repositoryPedido);

        this.controller =
                new PedidoController(repositoryPedido);
    }


    //Métods
    public void exibirMenuPedidos(){
                System.out.println("Cliente:");
                String nomeCliente = read.nextLine();

                List<Cliente> clientesEncontrados =
                        repositoryCliente.findByNomeContainingIgnoreCase(nomeCliente);

                if (clientesEncontrados.isEmpty()) {
                    System.out.println("Nenhum cliente encontrado.");
                    return;
                }

                System.out.println("Clientes encontrados:");

                clientesEncontrados.forEach(cliente ->
                        System.out.println(
                                cliente.getId() +
                                        " - " +
                                        cliente.getNome()
                        )
                );

                Long idCliente;
                System.out.println("Digite o ID do cliente:");

                try {
                    idCliente = Long.parseLong(read.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Digite um número válido.");
                    return;
                }

                Cliente cliente =
                        clientesEncontrados.stream()
                                .filter(c ->
                                        c.getId().equals(idCliente)
                                )
                                .findFirst()
                                .orElse(null);

                if (cliente == null) {
                    System.out.println("ID inválido.");
                    return;
                }

                List<ItemPedido> itens = new ArrayList<>();

                while (true) {
                    System.out.println("Produto (digite fim para encerrar):");
                    String busca = read.nextLine();

                    if (busca.equalsIgnoreCase("fim")) {
                        break;
                    }

                    List<Produto> encontrados =
                            repositoryProduto.findByNomeContainingIgnoreCase(busca);

                    if (encontrados.isEmpty()) {
                        System.out.println("Nenhum produto encontrado.");
                        continue;
                    }

                    System.out.println("Produtos encontrados:");

                    encontrados.forEach(produto ->
                            System.out.println(
                                    produto.getId() +
                                            " - " +
                                            produto.getNome()
                            )
                    );

                    System.out.println("Digite o ID do produto:");

                    Long idProduto =
                            Long.parseLong(read.nextLine());

                    Produto produtoSelecionado =
                            encontrados.stream()
                                    .filter(p ->
                                            p.getId().equals(idProduto)
                                    )
                                    .findFirst()
                                    .orElse(null);

                    if (produtoSelecionado == null) {
                        System.out.println("ID inválido.");
                        continue;
                    }

                    System.out.println("Quantidade:");

                    int quantidade =
                            Integer.parseInt(read.nextLine());

                    ItemPedido item = new ItemPedido();

                    item.setProduto(produtoSelecionado);
                    item.setQuantidade(quantidade);
                    item.setPrecoUnitario(
                            produtoSelecionado.getPreco()
                    );

                    itens.add(item);
                }
                Pedido pedido = new Pedido();
                pedido.setCliente(cliente);
                pedido.setItens(itens);
                pedido.setTotal(controller.calcularTotalPedido(itens));

                itens.forEach(
                        item -> item.setPedido(pedido)
                );

                controller.criar(pedido);
                System.out.println("Pedido cadastrado com sucesso!");
            }
        }