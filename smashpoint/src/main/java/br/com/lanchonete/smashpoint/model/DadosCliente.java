package br.com.lanchonete.smashpoint.model;

public record DadosCliente(String nome, String cpf) {

    @Override
    public String toString() {
        return nome + " - " + cpf;
    }
}
