package br.com.lanchonete.smashpoint.service.cliente;

import br.com.lanchonete.smashpoint.dto.responses.ClienteResponseDto;
import br.com.lanchonete.smashpoint.exception.ClienteNaoEncontradoException;
import br.com.lanchonete.smashpoint.model.Cliente;
import br.com.lanchonete.smashpoint.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuscarClientePorCpf {

    private final ClienteRepository clienteRepository;

    public ClienteResponseDto buscar(String cpf){
        Cliente cliente = clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado"));

        return gerarClienteResponse(cliente);
    }

    private ClienteResponseDto gerarClienteResponse(Cliente c) {
        return new ClienteResponseDto(
                c.getId(),
                c.getNome(),
                c.getCpf()
        );
    }
}
