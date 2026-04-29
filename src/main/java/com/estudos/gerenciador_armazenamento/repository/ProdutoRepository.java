package com.estudos.gerenciador_armazenamento.repository;

import com.estudos.gerenciador_armazenamento.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
    Optional<Produto> findBySku(String sku);
}
