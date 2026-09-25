package br.com.lanchonete.smashpoint.service.itempedido;

import br.com.lanchonete.smashpoint.dto.responses.ItemPedidoResponseDto;
import br.com.lanchonete.smashpoint.exception.ListaItemPedidoVaziaException;
import br.com.lanchonete.smashpoint.exception.PedidoInativoException;
import br.com.lanchonete.smashpoint.exception.PedidoNaoEncontradoException;
import br.com.lanchonete.smashpoint.model.ItemPedido;
import br.com.lanchonete.smashpoint.model.Pedido;
import br.com.lanchonete.smashpoint.model.Status;
import br.com.lanchonete.smashpoint.repository.ItemPedidoRepository;
import br.com.lanchonete.smashpoint.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListarItensPedidos {

    private final ItemPedidoRepository itemPedidoRepository;
    private final PedidoRepository pedidoRepository;

    public List<ItemPedidoResponseDto> listar(Long idPedido){
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado"));

        List<ItemPedido> itens = itemPedidoRepository.findByPedidoId(idPedido)
                .orElseThrow(() ->new ListaItemPedidoVaziaException("Nenhum item pedido realizado"));

        return gerarListaItemPedidoResponse(itens);
    }

    public List<ItemPedidoResponseDto> gerarListaItemPedidoResponse(List<ItemPedido> itemPedidos){
        return itemPedidos.stream()
                .map(i -> new ItemPedidoResponseDto(
                        i.getId(),
                        i.getProduto().getNome(),
                        i.getPedido().getNumeroMesa(),
                        i.getQuantidade(),
                        i.getPrecoUnitario().doubleValue(),
                        i.getTotal().doubleValue()
                )).toList();
    }

}
