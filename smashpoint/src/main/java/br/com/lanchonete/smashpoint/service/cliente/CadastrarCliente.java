package br.com.lanchonete.smashpoint.service.cliente;

import br.com.lanchonete.smashpoint.dto.requests.ClienteRequestDto;
import br.com.lanchonete.smashpoint.dto.responses.ClienteResponseDto;
import br.com.lanchonete.smashpoint.exception.ClienteExistenteNoBancoException;
import br.com.lanchonete.smashpoint.model.Cliente;
import br.com.lanchonete.smashpoint.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CadastrarCliente {

    private final ClienteRepository clienteRepository;

    public ClienteResponseDto cadastrar(ClienteRequestDto dto){
        if (clienteRepository.existsByCpf(dto.cpf())){
            throw new ClienteExistenteNoBancoException("Cliente existente");
        }

        Cliente cliente = new Cliente(dto.nome(),dto.cpf());
        Cliente clienteSalvo = clienteRepository.save(cliente);

        return ClienteResponseDto.fromEntity(clienteSalvo);
    }
}
