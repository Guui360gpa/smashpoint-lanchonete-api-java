package br.com.lanchonete.smashpoint.dto.responses;

public record ItemPedidoResponseDto (
        Long id,
        String nomeProduto,
        int quantidade,
        double precoUnitario,
        double subTotal
) {

}
