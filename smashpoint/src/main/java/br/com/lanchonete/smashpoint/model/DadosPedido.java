package br.com.lanchonete.smashpoint.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DadosPedido(int id, String title, int quantidade, double preco) {

    @JsonProperty("total")
    public String calcularTotal() {
        return String.format("%.2f",quantidade*preco);
    }
}
