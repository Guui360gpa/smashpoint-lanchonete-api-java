package br.com.lanchonete.smashpoint.service.pedido;

import br.com.lanchonete.smashpoint.dto.responses.PedidoResponseDto;
import br.com.lanchonete.smashpoint.exception.ListaPedidoVaziaException;
import br.com.lanchonete.smashpoint.model.Pedido;
import br.com.lanchonete.smashpoint.model.Status;
import br.com.lanchonete.smashpoint.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListarPedidosInativos {

    private final PedidoRepository pedidoRepository;

    public List<PedidoResponseDto> listar(){
        LocalDateTime inicioDoDia = LocalDate.now().atStartOfDay();
        LocalDateTime fimDoDia = LocalDate.now().atTime(23,59,59);

        Optional<List<Pedido>> pedidosEncontrados = pedidoRepository.findByStatusAndDataDesativacaoBetween(Status.DESATIVADO, inicioDoDia, fimDoDia);

        if (pedidosEncontrados.isEmpty()){
            throw new ListaPedidoVaziaException("Nenhum pedido desativado");
        }

        return pedidosEncontrados.get().stream()
                .map(PedidoResponseDto::fromEntity)
                .toList();
    }
}
