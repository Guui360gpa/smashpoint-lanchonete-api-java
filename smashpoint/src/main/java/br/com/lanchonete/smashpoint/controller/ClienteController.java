package br.com.lanchonete.smashpoint.controller;

import br.com.lanchonete.smashpoint.model.DadosCliente;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.*;

public class ClienteController implements Controller<DadosCliente> {

    private final File arquivoJson = new File("clientes.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private Map<Integer, DadosCliente> clientes = new HashMap<>();

    private void carregar() {
        try {
            if (arquivoJson.exists()) {
                clientes = mapper.readValue(
                        arquivoJson,
                        new TypeReference<Map<Integer, DadosCliente>>() {}
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void salvar() {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(arquivoJson, clientes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DadosCliente> listar() {
        carregar();
        return new ArrayList<>(clientes.values());
    }

    @Override
    public DadosCliente adicionar(DadosCliente cliente) {
        carregar();

        clientes.put(cliente.id(), cliente);

        salvar();
        return cliente;
    }

    @Override
    public DadosCliente buscar(int id) {
        carregar();
        return clientes.get(id);
    }
}