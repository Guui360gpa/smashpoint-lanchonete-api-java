package br.com.lanchonete.smashpoint.service.cliente;

import br.com.lanchonete.smashpoint.dto.responses.ClienteResponseDto;
import br.com.lanchonete.smashpoint.exception.ListaClientesVaziaException;
import br.com.lanchonete.smashpoint.model.Cliente;
import br.com.lanchonete.smashpoint.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarClientes {

    private final ClienteRepository clienteRepository;

    public List<ClienteResponseDto> listar(){
        List<Cliente> clientes = clienteRepository.findAll();

        if (clientes.isEmpty()){
            throw new ListaClientesVaziaException("Nenhum cliente cadastrado");
        }

        return clientes.stream()
                .map(ClienteResponseDto::fromEntity)
                .toList();
    }
}
