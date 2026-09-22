package br.com.lanchonete.smashpoint.dto.responses;

import br.com.lanchonete.smashpoint.model.Status;

public record ProdutoResponseDto(
        Long id,
        String nome,
        String descricao,
        String categoria,
        double preco,
        Status status
) {
}
