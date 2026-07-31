package br.com.lanchonete.smashpoint.service;

import br.com.lanchonete.smashpoint.model.Cliente;
import br.com.lanchonete.smashpoint.model.ItemPedido;
import br.com.lanchonete.smashpoint.model.Pedido;
import br.com.lanchonete.smashpoint.model.Produto;
import br.com.lanchonete.smashpoint.repository.ClienteRepository;
import br.com.lanchonete.smashpoint.repository.PedidoRepository;
import br.com.lanchonete.smashpoint.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

@Service
public class PedidoService {

    private List<Pedido> pedidos = new ArrayList<>();
    private Scanner read = new Scanner(System.in);

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;


    public void realizar() {
        Cliente cliente = selecionarCliente();
        if (cliente == null) {
            return;
        }

        List<ItemPedido> itens = coletarItensPedido();

        Pedido pedido = montarPedido(cliente, itens);

        criar(pedido);
        System.out.println("Pedido cadastrado com sucesso!");
    }

    private Cliente selecionarCliente() {
        System.out.println("Cliente:");
        String nomeCliente = read.nextLine();

        List<Cliente> clientesEncontrados =
                clienteRepository.findByNomeContainingIgnoreCase(nomeCliente);

        if (clientesEncontrados.isEmpty()) {
            System.out.println("Nenhum cliente encontrado.");
            return null;
        }

        System.out.println("Clientes encontrados:");
        clientesEncontrados.forEach(c ->
                System.out.println(c.getId() + " - " + c.getNome())
        );

        System.out.println("Digite o ID do cliente:");
        Long idCliente;
        try {
            idCliente = Long.parseLong(read.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Digite um número válido.");
            return null;
        }

        Cliente cliente = buscarPorId(clientesEncontrados, idCliente, Cliente::getId);

        if (cliente == null) {
            System.out.println("ID inválido.");
        }

        return cliente;
    }

    private List<ItemPedido> coletarItensPedido() {
        List<ItemPedido> itens = new ArrayList<>();

        while (true) {
            System.out.println("Produto (digite fim para encerrar):");
            String busca = read.nextLine();

            if (busca.equalsIgnoreCase("fim")) {
                break;
            }

            Produto produto = selecionarProduto(busca);
            if (produto == null) {
                continue;
            }

            ItemPedido item = criarItemPedido(produto);
            if (item != null) {
                itens.add(item);
            }
        }

        return itens;
    }

    private Produto selecionarProduto(String busca) {
        List<Produto> encontrados =
                produtoRepository.findByNomeContainingIgnoreCase(busca);

        if (encontrados.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
            return null;
        }

        System.out.println("Produtos encontrados:");
        encontrados.forEach(p ->
                System.out.println(p.getId() + " - " + p.getNome())
        );

        System.out.println("Digite o ID do produto:");

        Long idProduto;
        try {
            idProduto = Long.parseLong(read.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Digite um número válido.");
            return null;
        }

        Produto produto = buscarPorId(encontrados, idProduto, Produto::getId);

        if (produto == null) {
            System.out.println("ID inválido.");
        }

        return produto;
    }

    private ItemPedido criarItemPedido(Produto produto) {
        System.out.println("Quantidade:");

        int quantidade;
        try {
            quantidade = Integer.parseInt(read.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Digite um número válido.");
            return null;
        }

        ItemPedido item = new ItemPedido();
        item.setProduto(produto);
        item.setQuantidade(quantidade);
        item.setPrecoUnitario(produto.getPreco());

        return item;
    }

    private Pedido montarPedido(Cliente cliente, List<ItemPedido> itens) {
        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setItens(itens);
        pedido.setTotal(calcularTotalPedido(itens));

        itens.forEach(item -> item.setPedido(pedido));

        return pedido;
    }

    private <T> T buscarPorId(List<T> lista, Long id, Function<T, Long> idExtractor) {
        return lista.stream()
                .filter(item -> idExtractor.apply(item).equals(id))
                .findFirst()
                .orElse(null);
    }



    private void criar(Pedido pedido) {
        try {
            pedidoRepository.save(pedido);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ler() {
        pedidos = pedidoRepository.findAll();
        pedidos.forEach(System.out::println);
    }

    private BigDecimal calcularTotalPedido(
            List<ItemPedido> itens
    ) {

        return itens.stream()
                .map(item ->
                        item.getPrecoUnitario()
                                .multiply(
                                        BigDecimal.valueOf(
                                                item.getQuantidade()
                                        )
                                )
                )
                .reduce(
                        BigDecimal.ZERO,
                        BigDecimal::add
                );
    }
}
