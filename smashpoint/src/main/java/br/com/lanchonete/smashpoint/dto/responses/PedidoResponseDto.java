package br.com.lanchonete.smashpoint.dto.responses;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponseDto(
        Long id,
        int numeroMesa,
        String nomeCliente,
        double total
) {
}
