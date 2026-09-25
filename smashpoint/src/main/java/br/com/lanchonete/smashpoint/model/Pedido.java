package br.com.lanchonete.smashpoint.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
public class Pedido {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int numeroMesa;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(nullable = false)
    private LocalDateTime dataPedido;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(
            mappedBy = "pedido",
            cascade = CascadeType.ALL
    )
    private List<ItemPedido> itens = new ArrayList<>();

    public Pedido(Cliente cliente,int numeroMesa) {
        this.cliente = cliente;
        this.numeroMesa = numeroMesa;
        this.dataPedido = LocalDateTime.now();
        this.status = Status.ATIVADO;
    }

    public Pedido() {}

    public BigDecimal getTotal() {
        BigDecimal tot = BigDecimal.ZERO;
        for (ItemPedido i : itens){
            tot = tot.add(i.getTotal());
        }
        return tot;
    }
}
