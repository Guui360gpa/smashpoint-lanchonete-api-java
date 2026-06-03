package br.com.lanchonete.smashpoint.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String nome;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @Column(nullable = false,precision = 10,scale = 2)
    private BigDecimal preco;

    @OneToMany(mappedBy = "produto")
    private List<ItemPedido> itensPedidos = new ArrayList<>();

    public Produto() {}

    public Produto(String nome, String categoria, BigDecimal preco) {
        this.nome = nome;
        this.categoria = Categoria.fromString(categoria.trim());
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return String.format("%d - %s (%s) R$ %s", id, nome, categoria, preco);
    }
}
