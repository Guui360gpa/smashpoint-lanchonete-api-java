package br.com.lanchonete.smashpoint.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PedidoRequestDto(
        @NotBlank(message = "Campo obrigatório") int numeroMesa,
        @NotBlank(message = "Campo obrigatório") String cpfCliente
) {
}
