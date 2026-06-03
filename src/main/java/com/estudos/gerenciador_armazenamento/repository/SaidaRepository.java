package com.estudos.gerenciador_armazenamento.repository;

import com.estudos.gerenciador_armazenamento.domain.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaidaRepository extends JpaRepository<Movimentacao, Integer> {
    @Query("SELECT m FROM Movimentacao m WHERE m.produto.sku = :sku AND m.tipo = 'SAIDA'")
    List<Movimentacao> findByProdutoSku(@Param("sku") String sku);
}
