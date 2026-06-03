package com.estudos.gerenciador_armazenamento.dto.movimentacao;

import java.time.LocalDateTime;
import java.util.UUID;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UpdateMovimentacaoDto", description = "DTO para atualização de uma movimentação")
public record UpdateMovimentacaoDto(
        @Schema(description = "Nome do cliente", example = "João Silva")
        String cliente,
        
        @Schema(description = "ID do produto", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID produtoId,
        
        @Schema(description = "Quantidade movimentada", example = "10")
        int quantidade,
        
        @Schema(description = "Data e hora da movimentação", example = "2024-06-02T10:30:00")
        LocalDateTime dataMovimentacao) {
}
