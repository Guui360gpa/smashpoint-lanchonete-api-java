package br.com.lanchonete.smashpoint.model;



public record DadosProduto(String title, double price) {

    @Override
    public String toString() {
        return title + " - R$" + price;
    }
}
