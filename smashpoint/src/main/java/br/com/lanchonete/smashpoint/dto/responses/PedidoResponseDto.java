package br.com.lanchonete.smashpoint.dto.responses;

import br.com.lanchonete.smashpoint.model.Pedido;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponseDto(
        Long id,
        int numeroMesa,
        String nomeCliente,
        double total
) {
    public static PedidoResponseDto fromEntity(Pedido pedido) {
        return new PedidoResponseDto(
                pedido.getId(),
                pedido.getNumeroMesa(),
                pedido.getCliente().getNome(),
                pedido.getTotal().doubleValue()
        );
    }
}
