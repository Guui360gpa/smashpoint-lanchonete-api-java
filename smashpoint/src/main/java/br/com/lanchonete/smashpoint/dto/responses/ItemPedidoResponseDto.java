package br.com.lanchonete.smashpoint.dto.responses;

import br.com.lanchonete.smashpoint.model.ItemPedido;

public record ItemPedidoResponseDto (
        Long id,
        String nomeProduto,
        int numeroMesa,
        int quantidade,
        double precoUnitario
) {
}
