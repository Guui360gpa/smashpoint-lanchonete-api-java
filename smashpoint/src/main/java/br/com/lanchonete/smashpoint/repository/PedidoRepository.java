package br.com.lanchonete.smashpoint.repository;

import br.com.lanchonete.smashpoint.model.Pedido;
import br.com.lanchonete.smashpoint.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {

    Optional<List<Pedido>> findByStatus(Status status);
}
