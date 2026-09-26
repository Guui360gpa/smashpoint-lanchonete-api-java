package br.com.lanchonete.smashpoint.controller;

import br.com.lanchonete.smashpoint.dto.requests.ItemPedidoRequestDto;
import br.com.lanchonete.smashpoint.dto.responses.ItemPedidoResponseDto;
import br.com.lanchonete.smashpoint.service.itempedido.ListarItensPedidos;
import br.com.lanchonete.smashpoint.service.itempedido.NovoItemPedido;
import br.com.lanchonete.smashpoint.service.itempedido.RemoverItemPedido;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens")
@RequiredArgsConstructor
public class ItemPedidoController {

    private final NovoItemPedido novoItemPedido;
    private final ListarItensPedidos listarItensPedidos;
    private final RemoverItemPedido removerItemPedido;

    @PostMapping
    public ResponseEntity<ItemPedidoResponseDto> novo(@Valid @RequestBody ItemPedidoRequestDto dto){
        ItemPedidoResponseDto itemPedidoResponse = novoItemPedido.novo(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(itemPedidoResponse);
    }

    @GetMapping("/pedido/{idPedido}")
    public ResponseEntity<List<ItemPedidoResponseDto>> lista(@PathVariable Long idPedido){
        List<ItemPedidoResponseDto> itemPedidoResponses = listarItensPedidos.listar(idPedido);
        return ResponseEntity.ok(itemPedidoResponses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ItemPedidoResponseDto> remove(@PathVariable Long id){
        ItemPedidoResponseDto itemPedidoResponse = removerItemPedido.remover(id);
        return ResponseEntity.ok(itemPedidoResponse);
    }
}
