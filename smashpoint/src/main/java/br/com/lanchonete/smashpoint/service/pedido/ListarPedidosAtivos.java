package br.com.lanchonete.smashpoint.service.pedido;

import br.com.lanchonete.smashpoint.dto.responses.PedidoResponseDto;
import br.com.lanchonete.smashpoint.exception.ListaPedidoVaziaException;
import br.com.lanchonete.smashpoint.exception.ListaProdutosVaziaException;
import br.com.lanchonete.smashpoint.model.Pedido;
import br.com.lanchonete.smashpoint.model.Status;
import br.com.lanchonete.smashpoint.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListarPedidosAtivos {

    private final PedidoRepository pedidoRepository;

    public List<PedidoResponseDto> listar(){

        Optional<List<Pedido>> pedidosEncontrados = pedidoRepository.findByStatus(Status.ATIVADO);

        if (pedidosEncontrados.isEmpty()){
            throw new ListaPedidoVaziaException("Nenhum pedido ativo");
        }

        return pedidosEncontrados.get().stream()
                .map(PedidoResponseDto::fromEntity)
                .toList();
    }

}
