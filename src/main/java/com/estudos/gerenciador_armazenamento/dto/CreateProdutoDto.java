package com.estudos.gerenciador_armazenamento.dto;

import java.math.BigDecimal;

public record CreateProdutoDto(String sku, String nomeProduto, String descricaoProduto, BigDecimal precoProduto) {

}
