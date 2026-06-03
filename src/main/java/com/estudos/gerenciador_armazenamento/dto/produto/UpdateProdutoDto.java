package com.estudos.gerenciador_armazenamento.dto.produto;

import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UpdateProdutoDto", description = "DTO para atualização de um produto")
public record UpdateProdutoDto(
        @Schema(description = "SKU do produto", example = "SKU-001")
        String sku,
        
        @Schema(description = "Nome do produto", example = "Notebook Dell")
        String nomeProduto,
        
        @Schema(description = "Descrição do produto", example = "Notebook Dell Inspiron 15 com 16GB RAM")
        String descricaoProduto,
        
        @Schema(description = "Preço do produto", example = "2500.00")
        BigDecimal precoProduto) {
}
