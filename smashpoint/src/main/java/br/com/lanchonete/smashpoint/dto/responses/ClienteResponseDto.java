package br.com.lanchonete.smashpoint.dto.responses;

import java.util.List;

public record ClienteResponseDto(
        Long id,
        String nome,
        String cpf,
        List<PedidoResponseDto> pedidos
) {
}
