package io.github.paulosdoliiveira.mscartoes.infra.repository;

import io.github.paulosdoliiveira.mscartoes.model.ClienteCartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteCartaoRepository extends JpaRepository<ClienteCartao, Long> {
    List<ClienteCartao> findByCpf(String cpf);
}
