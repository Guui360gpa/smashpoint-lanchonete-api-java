package br.com.lanchonete.smashpoint.service.pedido;

import br.com.lanchonete.smashpoint.dto.responses.PedidoResponseDto;
import br.com.lanchonete.smashpoint.exception.ListaPedidoVaziaException;
import br.com.lanchonete.smashpoint.model.Pedido;
import br.com.lanchonete.smashpoint.model.Status;
import br.com.lanchonete.smashpoint.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListarPedidosInativos {

    private final PedidoRepository pedidoRepository;

    //Listar pedidos inativos apenas do dia em que foi desativado
    public List<PedidoResponseDto> listar(){

        Optional<List<Pedido>> pedidosEncontrados = pedidoRepository.findByStatus(Status.DESATIVADO);

        if (pedidosEncontrados.isEmpty()){
            throw new ListaPedidoVaziaException("Nenhum pedido desativado");
        }

        return gerarListaPedidoResponse(pedidosEncontrados.get());
    }

    private List<PedidoResponseDto> gerarListaPedidoResponse(List<Pedido> pedidos){
        return pedidos.stream()
                .map(p -> new PedidoResponseDto(
                        p.getId(),
                        p.getNumeroMesa(),
                        p.getCliente().getNome(),
                        p.getTotal().doubleValue()
                )).toList();
    }

}
