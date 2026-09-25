package br.com.lanchonete.smashpoint.repository;

import br.com.lanchonete.smashpoint.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido,Long> {

    Optional<List<ItemPedido>> findByPedidoId(Long idPedido);

}
