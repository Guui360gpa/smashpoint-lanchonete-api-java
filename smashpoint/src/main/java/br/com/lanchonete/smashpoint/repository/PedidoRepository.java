package br.com.lanchonete.smashpoint.repository;

import br.com.lanchonete.smashpoint.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {
}
