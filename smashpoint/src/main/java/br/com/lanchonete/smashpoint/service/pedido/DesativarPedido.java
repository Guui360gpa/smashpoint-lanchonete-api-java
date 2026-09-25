package br.com.lanchonete.smashpoint.service.pedido;

import br.com.lanchonete.smashpoint.dto.responses.PedidoResponseDto;
import br.com.lanchonete.smashpoint.exception.PedidoJaDesativadoException;
import br.com.lanchonete.smashpoint.exception.PedidoNaoEncontradoException;
import br.com.lanchonete.smashpoint.model.Pedido;
import br.com.lanchonete.smashpoint.model.Status;
import br.com.lanchonete.smashpoint.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DesativarPedido {

    private final PedidoRepository pedidoRepository;

    public PedidoResponseDto desativar(Long id){
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado"));

        if (pedido.getStatus().equals(Status.DESATIVADO)){
            throw new PedidoJaDesativadoException("Pedido já inativo");
        }

        if (pedido.getDataDesativacao() == null) { // só seta na primeira vez
            pedido.setDataDesativacao(LocalDateTime.now());
        }
        pedido.setStatus(Status.DESATIVADO);
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        return PedidoResponseDto.fromEntity(pedidoSalvo);
    }
}
