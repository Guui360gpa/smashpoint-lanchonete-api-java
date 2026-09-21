package br.com.lanchonete.smashpoint.dto.responses;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponseDto(
        Long id,
        String nomeCliente,
        double total,
        LocalDateTime dataPedido,
        List<ItemPedidoResponseDto> itensPedidos
) {
}
