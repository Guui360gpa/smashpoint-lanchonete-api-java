package br.com.lanchonete.smashpoint.dto.responses;

public record ItemPedidoResponseDto (
        Long id,
        String nomeProduto,
        int numeroMesa,
        int quantidade,
        double precoUnitario
) {

}
