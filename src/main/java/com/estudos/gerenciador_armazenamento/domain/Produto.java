package com.estudos.gerenciador_armazenamento.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Produto")
@Schema(name = "Produto", description = "Entidade que representa um produto no armazenamento")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Schema(description = "ID único do produto", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;

    @Column
    @Schema(description = "SKU do produto", example = "SKU-001")
    private String sku;

    @Column
    @Schema(description = "Nome do produto", example = "Notebook Dell")
    private String nomeProduto;

    @Column
    @Schema(description = "Descrição do produto", example = "Notebook Dell Inspiron 15")
    private String descricaoProduto;

    @Column
    @Schema(description = "Preço do produto", example = "2500.00")
    private BigDecimal precoProduto;
}
