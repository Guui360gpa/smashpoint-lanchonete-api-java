package br.com.lanchonete.smashpoint.controller;

import br.com.lanchonete.smashpoint.dto.requests.ProdutoRequestDto;
import br.com.lanchonete.smashpoint.dto.responses.ProdutoResponseDto;
import br.com.lanchonete.smashpoint.service.produto.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController{

    private final CadastrarProduto cadastrarProduto;
    private final ListarProdutosAtivos listarProdutosAtivos;
    private final ListarProdutosInativos listarProdutosInativos;
    private final BuscarProdutoPorNome buscarProduto;
    private final DesativarProduto desativarProduto;
    private final AtivarProduto ativarProduto;

    @PostMapping
    public ResponseEntity<ProdutoResponseDto> cadastro(@Valid @RequestBody ProdutoRequestDto dto){
        ProdutoResponseDto produtoResponse = cadastrarProduto.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoResponse);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<ProdutoResponseDto> busca(@PathVariable String nome){
        ProdutoResponseDto produtoResponse = buscarProduto.buscar(nome);
        return ResponseEntity.ok(produtoResponse);
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<ProdutoResponseDto>> listaAtivo(){
        List<ProdutoResponseDto> produtoResponses = listarProdutosAtivos.listar();
        return ResponseEntity.ok(produtoResponses);
    }

    @GetMapping("/inativos")
    public ResponseEntity<List<ProdutoResponseDto>> listaInativo(){
        List<ProdutoResponseDto> produtoResponses = listarProdutosInativos.listar();
        return ResponseEntity.ok(produtoResponses);
    }

    @PatchMapping("/{nome}/desativar")
    public ResponseEntity<ProdutoResponseDto> desativa(@PathVariable String nome){
        ProdutoResponseDto produtoResponse = desativarProduto.desativar(nome);
        return ResponseEntity.ok(produtoResponse);
    }

    @PatchMapping("/{nome}/ativar")
    public ResponseEntity<ProdutoResponseDto> ativa(@PathVariable String nome){
        ProdutoResponseDto produtoResponse = ativarProduto.ativar(nome);
        return ResponseEntity.ok(produtoResponse);
    }
}
