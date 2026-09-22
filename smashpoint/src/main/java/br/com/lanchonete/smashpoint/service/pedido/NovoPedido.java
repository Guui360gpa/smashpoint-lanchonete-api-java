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
import br.com.lanchonete.smashpoint.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NovoPedido {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;

    public PedidoResponseDto novo(PedidoRequestDto dto){
        Cliente cliente = clienteRepository.findByCpf(dto.cpfCliente())
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado"));

        Pedido pedido = new Pedido(cliente,dto.numeroMesa());

        Optional<List<Pedido>> pedidosAtivosEncontrados = pedidoRepository.findByStatus(Status.ATIVADO);

        if (pedidosAtivosEncontrados.isPresent()){
            for (Pedido p : pedidosAtivosEncontrados.get()){
                if (pedido.getCliente().getCpf().equals(p.getCliente().getCpf())){
                    throw new ClienteComPedidoAtivoJaExistenteException("Cliente com pedido ativo existente");
                }
            }
        }

        Pedido pedidoSalvo = salvarPedidoNoBanco(pedido);

        return gerarPedidoResponse(pedidoSalvo);
    }

    private PedidoResponseDto gerarPedidoResponse(Pedido p) {
        return new PedidoResponseDto(
                p.getId(),
                p.getNumeroMesa(),
                p.getCliente().getNome(),
                p.getTotal().doubleValue()

        );
    }

    private Pedido salvarPedidoNoBanco(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
}
