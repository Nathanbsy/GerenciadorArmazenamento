package com.estudos.gerenciador_armazenamento.service;

import com.estudos.gerenciador_armazenamento.domain.Produto;
import com.estudos.gerenciador_armazenamento.dto.CreateProdutoDto;
import com.estudos.gerenciador_armazenamento.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProdutoService {


    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public UUID criarProduto(CreateProdutoDto produtoDto){

        Produto produto = new Produto(UUID.randomUUID(), produtoDto.sku(), produtoDto.nomeProduto(), produtoDto.descricaoProduto(), produtoDto.precoProduto());

        Produto produtoSalvo = produtoRepository.save(produto);

        return produtoSalvo.getId();
    }
}
