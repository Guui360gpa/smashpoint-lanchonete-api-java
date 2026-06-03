package br.com.lanchonete.smashpoint.controller;

import br.com.lanchonete.smashpoint.model.ItemPedido;
import br.com.lanchonete.smashpoint.model.Pedido;
import br.com.lanchonete.smashpoint.repository.PedidoRepository;
import java.math.BigDecimal;
import java.util.*;

public class PedidoController {

    private List<Pedido> pedidos = new ArrayList<>();
    private PedidoRepository repository;

    public PedidoController(PedidoRepository repository) {
        this.repository = repository;

        if (repository == null) {
            System.out.println("Repository veio NULL");
        }
    }

    public void criar(Pedido pedido) {
        try {
            repository.save(pedido);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ler() {
        pedidos = repository.findAll();
        pedidos.forEach(System.out::println);
    }

    public BigDecimal calcularTotalPedido(
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
