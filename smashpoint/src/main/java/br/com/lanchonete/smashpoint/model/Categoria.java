package br.com.lanchonete.smashpoint.model;

public enum Categoria {
    LANCHES("Lanches"),
    SALGADOS("Salgados"),
    BEBIDAS("Bebidas"),
    SOBREMESAS("Sobremesas");

    private String categoria;

    Categoria(String categoria) {
        this.categoria = categoria;
    }

    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoria.equalsIgnoreCase(text)){
                return categoria;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }
}
