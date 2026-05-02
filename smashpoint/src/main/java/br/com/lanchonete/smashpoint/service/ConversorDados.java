package br.com.lanchonete.smashpoint.service;

import br.com.lanchonete.smashpoint.model.DadosProduto;
import br.com.lanchonete.smashpoint.model.DadosProdutoAPI;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Map;

public class ConversorDados implements IConversorDados{
    private ObjectMapper mapper = new ObjectMapper();



    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        JsonNode root = mapper.readTree(json);

        for (JsonNode p : root.get("products")){

            DadosProdutoAPI produto = new DadosProdutoAPI(
                    p.get("id").asInt(),
                    p.get("title").asText(),
                    p.get("price").asDouble()
            );

            System.out.printf("%s - R$ %.2f\n\n",produto.title(),produto.price());
        }
        return null;
    }

}

