package br.com.lanchonete.smashpoint.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DadosPedido(DadosProduto produto, int quantidade) {

    @JsonProperty("total")
    public String calcularTotal() {
        return String.format("%.2f",quantidade* produto().price());
    }

    @Override
    public String toString() {
        return produto +
                " quantidade: " + quantidade;
    }
}
