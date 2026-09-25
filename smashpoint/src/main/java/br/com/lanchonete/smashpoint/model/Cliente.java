package br.com.lanchonete.smashpoint.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column(nullable = false,unique = true)
    private String cpf;

    @Column(nullable = false)
    private LocalDate dataCadastro;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente(String nome,String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataCadastro = LocalDate.now();
    }

    public Cliente(String cpf) {
        this.cpf = cpf;
        this.dataCadastro = LocalDate.now();
    }

    public Cliente() {}
}
