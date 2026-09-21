package br.com.lanchonete.smashpoint.dto.responses;

public record ProdutoResponseDto(
        Long id,
        String nome,
        String descricao,
        String categoria,
        double preco
) {
}
