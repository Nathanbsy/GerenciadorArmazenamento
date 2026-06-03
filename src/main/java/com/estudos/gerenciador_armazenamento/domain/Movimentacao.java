package com.estudos.gerenciador_armazenamento.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Movimentacao")
@Schema(name = "Movimentacao", description = "Entidade que representa uma movimentação de entrada ou saída de produtos")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único da movimentação", example = "1")
    private int id;

    @Column
    @Schema(description = "Nome do cliente", example = "João Silva")
    private String cliente;

    @ManyToOne
    @JoinColumn(name = "ProdutoId", nullable = false)
    @Schema(description = "Produto associado à movimentação")
    private Produto produto;

    @Column(nullable = false)
    @Schema(description = "Tipo de movimentação", example = "ENTRADA", allowableValues = {"ENTRADA", "SAIDA"})
    private String tipo;

    @Column(nullable = false)
    @Schema(description = "Quantidade movimentada", example = "10")
    private int quantidade;

    @Column(nullable = false)
    @Schema(description = "Data e hora da movimentação", example = "2024-06-02T10:30:00")
    private LocalDateTime dataMovimentacao;
}