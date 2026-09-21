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
        if (clienteRepository.findAll().isEmpty()){
            throw new ListaClientesVaziaException("Nenhum cliente cadastrado");
        }
        List<Cliente> clientes = clienteRepository.findAll();

        return gerarListaClienteResponse(clientes);
    }

    private List<ClienteResponseDto> gerarListaClienteResponse(List<Cliente> cs){
        return cs.stream()
                .map(c -> new ClienteResponseDto(
                        c.getId(),
                        c.getNome(),
                        c.getCpf()
                )).toList();
    }
}
