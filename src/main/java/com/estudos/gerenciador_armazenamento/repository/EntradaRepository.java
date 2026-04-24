package com.estudos.gerenciador_armazenamento.repository;

import com.estudos.gerenciador_armazenamento.domain.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EntradaRepository extends JpaRepository<Movimentacao, UUID> {
}
