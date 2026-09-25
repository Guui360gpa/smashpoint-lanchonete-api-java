package br.com.lanchonete.smashpoint.dto.responses;

import br.com.lanchonete.smashpoint.model.Produto;
import br.com.lanchonete.smashpoint.model.Status;

public record ProdutoResponseDto(
        Long id,
        String nome,
        String descricao,
        String categoria,
        double preco,
        Status status
) {
    public static ProdutoResponseDto fromEntity(Produto produto) {
        return new ProdutoResponseDto(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getCategoria().toString(),
                produto.getPreco().doubleValue(),
                produto.getStatus()
        );
    }
}
