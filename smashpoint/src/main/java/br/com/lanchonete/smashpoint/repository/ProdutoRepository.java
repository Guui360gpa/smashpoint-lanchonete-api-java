package br.com.lanchonete.smashpoint.repository;

import br.com.lanchonete.smashpoint.model.Produto;
import br.com.lanchonete.smashpoint.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByNomeContainingIgnoreCase(String nome);

    boolean existsByNome(String nome);

    List<Produto> findByStatus(Status status);

    Optional<Produto> findByNome(String nome);
}
