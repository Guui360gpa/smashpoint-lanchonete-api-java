package br.com.lanchonete.smashpoint.repository;

import br.com.lanchonete.smashpoint.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    List<Cliente> findByNomeContainingIgnoreCase(String nome);

    boolean existsByCpf(String cpf);

    Optional<Cliente> findByCpf(String cpf);
}
