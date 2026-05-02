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

public class PedidoController implements Controller<DadosPedido> {

    private final File arquivoJson = new File("pedidos.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private Map<Integer, DadosPedido> pedidos = new HashMap<>();



    private void carregar() {
        try {
            if (arquivoJson.exists()) {
                pedidos = mapper.readValue(
                        arquivoJson,
                        new TypeReference<Map<Integer, DadosPedido>>() {}
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



    @Override
    public List<DadosPedido> listar() {
        carregar();
        return new ArrayList<>(pedidos.values());
    }

    @Override
    public DadosPedido adicionar(DadosPedido pedido) {
        carregar();

        pedidos.put(pedido.id(), pedido);

        salvar();
        return pedido;
    }

    @Override
    public DadosPedido buscar(int id) {
        carregar();

        return pedidos.get(id);
    }
}
