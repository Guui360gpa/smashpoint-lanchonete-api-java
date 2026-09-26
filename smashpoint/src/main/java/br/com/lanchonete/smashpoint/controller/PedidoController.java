package br.com.lanchonete.smashpoint.controller;


import br.com.lanchonete.smashpoint.dto.requests.PedidoRequestDto;
import br.com.lanchonete.smashpoint.dto.responses.PedidoResponseDto;
import br.com.lanchonete.smashpoint.service.pedido.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController{

    private final NovoPedido novoPedido;
    private final ListarPedidosAtivos listarPedidosAtivos;
    private final ListarPedidosInativos listarPedidosInativos;
    private final AtivarPedido ativarPedido;
    private final DesativarPedido desativarPedido;

    @PostMapping
    public ResponseEntity<PedidoResponseDto> novo(@Valid @RequestBody PedidoRequestDto dto){
        PedidoResponseDto pedidoResponse = novoPedido.novo(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoResponse);
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<PedidoResponseDto>> listaAtivo(){
        List<PedidoResponseDto> pedidoResponses = listarPedidosAtivos.listar();
        return ResponseEntity.ok(pedidoResponses);
    }

    @GetMapping("/inativos")
    public ResponseEntity<List<PedidoResponseDto>> listaInativos(){
        List<PedidoResponseDto> pedidoResponses = listarPedidosInativos.listar();
        return ResponseEntity.ok(pedidoResponses);
    }

    @PatchMapping("/{id}/desativar")
    public ResponseEntity<PedidoResponseDto> desativa(@PathVariable Long id){
        PedidoResponseDto pedidoResponse = desativarPedido.desativar(id);
        return ResponseEntity.ok(pedidoResponse);
    }

    @PatchMapping("/{id}/ativar")
    public ResponseEntity<PedidoResponseDto> ativa(@PathVariable Long id){
        PedidoResponseDto pedidoResponse = ativarPedido.ativar(id);
        return ResponseEntity.ok(pedidoResponse);
    }


}