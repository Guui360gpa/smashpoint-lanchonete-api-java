package br.com.lanchonete.smashpoint.controller;

import br.com.lanchonete.smashpoint.model.DadosProduto;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.*;

public class ProdutoController implements Controller<DadosProduto> {

    private final File arquivoJson = new File("produtos.json");
    private final ObjectMapper mapper = new ObjectMapper();
    private Map<Integer, DadosProduto> produtos = new HashMap<>();

    private void carregar() {
        try {
            if (arquivoJson.exists()) {
                produtos = mapper.readValue(
                        arquivoJson,
                        new TypeReference<Map<Integer, DadosProduto>>() {}
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void salvar() {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(arquivoJson, produtos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DadosProduto> listar() {
        carregar();
        return new ArrayList<>(produtos.values());
    }

    @Override
    public DadosProduto adicionar(DadosProduto produto) {
        carregar();

        produtos.put(produto.id(), produto);

        salvar();
        return produto;
    }

    @Override
    public DadosProduto buscar(int id) {
        carregar();
        return produtos.get(id);
    }
}