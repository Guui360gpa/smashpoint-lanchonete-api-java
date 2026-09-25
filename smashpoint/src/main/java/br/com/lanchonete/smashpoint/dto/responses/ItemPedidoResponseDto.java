package br.com.lanchonete.smashpoint.dto.responses;

import br.com.lanchonete.smashpoint.model.ItemPedido;

public record ItemPedidoResponseDto (
        Long id,
        String nomeProduto,
        int numeroMesa,
        int quantidade,
        double precoUnitario,
        double total
) {
    public static ItemPedidoResponseDto fromEntity(ItemPedido item) {
        return new ItemPedidoResponseDto(
                item.getId(),
                item.getProduto().getNome(),
                item.getPedido().getNumeroMesa(),
                item.getQuantidade(),
                item.getPrecoUnitario().doubleValue(),
                item.getTotal().doubleValue()
        );
    }
}
