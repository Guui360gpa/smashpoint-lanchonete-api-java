package br.com.lanchonete.smashpoint.service.itempedido;

import br.com.lanchonete.smashpoint.dto.requests.ItemPedidoRequestDto;
import br.com.lanchonete.smashpoint.dto.responses.ItemPedidoResponseDto;
import br.com.lanchonete.smashpoint.exception.*;
import br.com.lanchonete.smashpoint.model.ItemPedido;
import br.com.lanchonete.smashpoint.model.Pedido;
import br.com.lanchonete.smashpoint.model.Produto;
import br.com.lanchonete.smashpoint.model.Status;
import br.com.lanchonete.smashpoint.repository.ItemPedidoRepository;
import br.com.lanchonete.smashpoint.repository.PedidoRepository;
import br.com.lanchonete.smashpoint.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NovoItemPedido {

    private final ItemPedidoRepository itemPedidoRepository;
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    public ItemPedidoResponseDto novo(ItemPedidoRequestDto dto){

        Pedido pedido = pedidoRepository.findById(dto.idPedido())
                .orElseThrow(() -> new PedidoNaoEncontradoException("Pedido não encontrado"));

        if (pedido.getStatus().equals(Status.DESATIVADO)){
            throw new PedidoInativoException("Pedido inativo para um novo item");
        }

        Produto produto = produtoRepository.findById(dto.idProduto())
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));

        if (produto.getStatus().equals(Status.DESATIVADO)){
            throw new ProdutoInativoException("Produto inativo para um novo item");
        }

        if (dto.quantidade() > 99){
            throw new ExcedeLimiteQuantidadeException("Quantidade fora do limite.");
        }

        ItemPedido itemPedido = salvarItemPedidoNoBanco(new ItemPedido(pedido,produto,dto.quantidade()));

        return gerarItemPedidoResponse(itemPedido);
    }

    private ItemPedidoResponseDto gerarItemPedidoResponse(ItemPedido i) {
        return new ItemPedidoResponseDto(
                i.getId(),
                i.getProduto().getNome(),
                i.getPedido().getNumeroMesa(),
                i.getQuantidade(),
                i.getPrecoUnitario().doubleValue(),
                i.getTotal().doubleValue()
        );
    }

    private ItemPedido salvarItemPedidoNoBanco(ItemPedido itemPedido) {
        return itemPedidoRepository.save(itemPedido);
    }
}
