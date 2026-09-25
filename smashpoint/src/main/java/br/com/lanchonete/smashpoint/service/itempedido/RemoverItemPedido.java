package br.com.lanchonete.smashpoint.service.itempedido;

import br.com.lanchonete.smashpoint.dto.responses.ItemPedidoResponseDto;
import br.com.lanchonete.smashpoint.exception.ItemPedidoNaoEncontradoException;
import br.com.lanchonete.smashpoint.exception.PedidoNaoEncontradoException;
import br.com.lanchonete.smashpoint.model.ItemPedido;
import br.com.lanchonete.smashpoint.model.Pedido;
import br.com.lanchonete.smashpoint.repository.ItemPedidoRepository;
import br.com.lanchonete.smashpoint.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RemoverItemPedido {

    private final ItemPedidoRepository itemPedidoRepository;
    private final PedidoRepository pedidoRepository;

    public ItemPedidoResponseDto remover(Long idItemPedido){
        ItemPedido item = itemPedidoRepository.findById(idItemPedido)
                .orElseThrow(() -> new ItemPedidoNaoEncontradoException("Item Pedido Nao Encontrado"));

        Pedido pedido = pedidoRepository.findByItensId(idItemPedido)
                .orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado"));

        pedido.getItens().removeIf(i -> i.getId().equals(idItemPedido));
        pedidoRepository.save(pedido);

        itemPedidoRepository.delete(item);

        return ItemPedidoResponseDto.fromEntity(item);

    }
}
