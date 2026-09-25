package br.com.lanchonete.smashpoint.dto.requests;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record ClienteRequestDto(
        String nome,
        @NotBlank(message = "Campo obrigatório") @CPF(message = "CPF inválido") String cpf
) {
}
