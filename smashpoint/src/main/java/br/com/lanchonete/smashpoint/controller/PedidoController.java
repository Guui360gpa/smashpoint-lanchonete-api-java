package br.com.lanchonete.smashpoint.controller;

import br.com.lanchonete.smashpoint.model.DadosPedido;
import br.com.lanchonete.smashpoint.model.DadosProduto;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PedidoController {

    private final File arquivoJson = new File("pedidos.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private Map<Integer, List<DadosPedido>> pedidos = new HashMap<>();



    private void carregar() {
        try {
            if (arquivoJson.exists()) {
                pedidos = mapper.readValue(
                        arquivoJson,
                        new TypeReference<Map<Integer, List<DadosPedido>>>() {}
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void salvar() {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(arquivoJson, pedidos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public List<DadosPedido> listar() {
        carregar();

        List<DadosPedido> lista = new ArrayList<>();

        pedidos.values().forEach(lista::addAll);

        return lista;
    }

    public void adicionar(List<DadosPedido> pedido) {
        carregar();

        pedidos.put(pedidos.size() + 1, pedido);

        salvar();
    }

    public List<DadosPedido> buscar(String nome) {
        carregar();

        return null;
    }
}
