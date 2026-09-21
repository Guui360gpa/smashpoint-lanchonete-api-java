package br.com.lanchonete.smashpoint.model;

public enum Status {
    ATIVADO("Ativado"),
    DESATIVADO("Desativado");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public static Status fromString(String text) {
        for (Status status : Status.values()) {
            if (status.status.equalsIgnoreCase(text)){
                return status;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
