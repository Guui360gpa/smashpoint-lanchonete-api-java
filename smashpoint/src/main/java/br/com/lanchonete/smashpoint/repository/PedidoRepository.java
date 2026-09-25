package br.com.lanchonete.smashpoint.repository;

import br.com.lanchonete.smashpoint.model.Pedido;
import br.com.lanchonete.smashpoint.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido,Long> {

    Optional<List<Pedido>> findByStatus(Status status);

    Optional<Pedido> findByItensId(Long idItemPedido);

    boolean existsByClienteCpfAndStatus(String cpf, Status status);

    @Query("SELECT p FROM Pedido p WHERE p.status = :status AND p.dataDesativacao BETWEEN :inicio AND :fim")
    Optional<List<Pedido>> findByStatusAndDataDesativacaoBetween(
            @Param("status") Status status,
            @Param("inicio") LocalDateTime inicio,
            @Param("fim") LocalDateTime fim
    );
}
