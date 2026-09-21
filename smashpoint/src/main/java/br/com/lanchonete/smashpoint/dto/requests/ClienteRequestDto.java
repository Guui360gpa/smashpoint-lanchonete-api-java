package br.com.lanchonete.smashpoint.dto.requests;

import jakarta.validation.constraints.NotBlank;

public record ClienteRequestDto(
        @NotBlank(message = "Campo obrigatório") String nome,
        @NotBlank(message = "Campo obrigatório") String cpf
) {
}
