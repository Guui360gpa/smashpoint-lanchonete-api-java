package br.com.lanchonete.smashpoint.dto.responses;

import br.com.lanchonete.smashpoint.model.Cliente;

import java.util.List;

public record ClienteResponseDto(
        Long id,
        String nome,
        String cpf
) {
    public static ClienteResponseDto fromEntity(Cliente cliente) {
        return new ClienteResponseDto(cliente.getId(), cliente.getNome(), cliente.getCpf());
    }
}
