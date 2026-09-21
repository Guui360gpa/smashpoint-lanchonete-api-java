package br.com.lanchonete.smashpoint.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemPedidoRequestDto(
        @NotNull Long idPedido,
        @NotBlank(message = "Campo obrigatório") String idProduto,
        @NotNull(message = "Campo obrigatório") int quantidade
) {
}
