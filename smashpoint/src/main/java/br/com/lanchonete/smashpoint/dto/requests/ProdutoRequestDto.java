package br.com.lanchonete.smashpoint.dto.requests;

import jakarta.validation.constraints.NotBlank;

public record ProdutoRequestDto(
        @NotBlank(message = "Campo obrigatório") String nome,
        @NotBlank(message = "Campo obrigatório") String descricao,
        @NotBlank(message = "Campo obrigatório") String castegoria,
        @NotBlank(message = "Campo obrigatório") double preco
) {
}
