package br.com.lanchonete.smashpoint.service.pedido;

import br.com.lanchonete.smashpoint.dto.requests.PedidoRequestDto;
import br.com.lanchonete.smashpoint.dto.responses.PedidoResponseDto;
import br.com.lanchonete.smashpoint.exception.ClienteComPedidoAtivoJaExistenteException;
import br.com.lanchonete.smashpoint.exception.ClienteNaoEncontradoException;
import br.com.lanchonete.smashpoint.model.Cliente;
import br.com.lanchonete.smashpoint.model.Pedido;
import br.com.lanchonete.smashpoint.model.Status;
import br.com.lanchonete.smashpoint.repository.ClienteRepository;
import br.com.lanchonete.smashpoint.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NovoPedido {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;

    public PedidoResponseDto novo(PedidoRequestDto dto){
        Cliente cliente = clienteRepository.findByCpf(dto.cpfCliente())
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado"));

        if (pedidoRepository.existsByClienteCpfAndStatus(dto.cpfCliente(), Status.ATIVADO)) {
            throw new ClienteComPedidoAtivoJaExistenteException("Cliente com pedido ativo existente");
        }

        Pedido pedido = new Pedido(cliente,dto.numeroMesa());
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        return PedidoResponseDto.fromEntity(pedidoSalvo);
    }
}
