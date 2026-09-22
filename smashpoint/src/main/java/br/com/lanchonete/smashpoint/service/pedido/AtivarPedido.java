package br.com.lanchonete.smashpoint.service.pedido;

import br.com.lanchonete.smashpoint.dto.responses.PedidoResponseDto;
import br.com.lanchonete.smashpoint.exception.PedidoJaDesativadoException;
import br.com.lanchonete.smashpoint.exception.PedidoNaoEncontradoException;
import br.com.lanchonete.smashpoint.model.Pedido;
import br.com.lanchonete.smashpoint.model.Status;
import br.com.lanchonete.smashpoint.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtivarPedido {

    private final PedidoRepository pedidoRepository;

    //Só poderá ativar pedido no prazo de 24 horas depois de ser desativado
    public PedidoResponseDto ativar(Long id){
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado"));

        if (pedido.getStatus().equals(Status.ATIVADO)){
            throw new PedidoJaDesativadoException("Pedido já ativo");
        }

        pedido.setStatus(Status.ATIVADO);
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
