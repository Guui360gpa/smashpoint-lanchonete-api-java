package br.com.lanchonete.smashpoint.controller;

import br.com.lanchonete.smashpoint.dto.requests.ClienteRequestDto;
import br.com.lanchonete.smashpoint.dto.responses.ClienteResponseDto;
import br.com.lanchonete.smashpoint.service.cliente.BuscarClientePorCpf;
import br.com.lanchonete.smashpoint.service.cliente.CadastrarCliente;
import br.com.lanchonete.smashpoint.service.cliente.ListarClientes;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController{

    private final CadastrarCliente cadastrarCliente;
    private final BuscarClientePorCpf buscarClientePorCpf;
    private final ListarClientes listarClientes;

    @PostMapping
    public ResponseEntity<ClienteResponseDto> cadastro(@Valid  @RequestBody ClienteRequestDto dto){
        ClienteResponseDto clienteResponse = cadastrarCliente.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteResponse);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDto>> lista(){
        List<ClienteResponseDto> clienteResponses = listarClientes.listar();
        return ResponseEntity.ok(clienteResponses);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteResponseDto> buscar(@PathVariable String cpf){
        ClienteResponseDto clienteResponse = buscarClientePorCpf.buscar(cpf);
        return ResponseEntity.ok(clienteResponse);
    }
}
