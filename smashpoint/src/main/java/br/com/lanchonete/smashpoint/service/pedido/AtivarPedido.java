package br.com.lanchonete.smashpoint.service.pedido;

import br.com.lanchonete.smashpoint.dto.responses.PedidoResponseDto;
import br.com.lanchonete.smashpoint.exception.PedidoJaDesativadoException;
import br.com.lanchonete.smashpoint.exception.PedidoNaoEncontradoException;
import br.com.lanchonete.smashpoint.exception.PrazoReativacaoExpiradoException;
import br.com.lanchonete.smashpoint.model.Pedido;
import br.com.lanchonete.smashpoint.model.Status;
import br.com.lanchonete.smashpoint.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AtivarPedido {

    private final PedidoRepository pedidoRepository;

    public PedidoResponseDto ativar(Long id){
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado"));

        if (pedido.getStatus().equals(Status.ATIVADO)){
            throw new PedidoJaDesativadoException("Pedido já ativo");
        }

        if (pedido.getDataDesativacao().isBefore(LocalDateTime.now().minusHours(24))) {
            throw new PrazoReativacaoExpiradoException("Prazo de 24 horas para reativar o pedido expirou");
        }

        pedido.setStatus(Status.ATIVADO);
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        return PedidoResponseDto.fromEntity(pedidoSalvo);
    }
}
