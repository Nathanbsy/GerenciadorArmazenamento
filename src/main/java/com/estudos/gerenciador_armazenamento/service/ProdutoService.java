package com.estudos.gerenciador_armazenamento.service;

import com.estudos.gerenciador_armazenamento.domain.Produto;
import com.estudos.gerenciador_armazenamento.dto.CreateProdutoDto;
import com.estudos.gerenciador_armazenamento.dto.UpdateProdutoDto;
import com.estudos.gerenciador_armazenamento.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {


    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public UUID criarProduto(CreateProdutoDto produtoDto){

        Produto produto = new Produto(null, produtoDto.sku(), produtoDto.nomeProduto(), produtoDto.descricaoProduto(), produtoDto.precoProduto());

        Produto produtoSalvo = produtoRepository.save(produto);

        return produtoSalvo.getId();
    }

    public Optional<Produto> buscarProdutoPorSku(String sku){
        return produtoRepository.findBySku(sku);
    }

    public Optional<Produto> buscarProdutoPorId(UUID id){
        return produtoRepository.findById(id);
    }

    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }

    public void atualizarProduto(UUID produtoId, UpdateProdutoDto body){
        var id = UUID.fromString(produtoId.toString());

        var produtoExistente = produtoRepository.findById(id);

        if(produtoExistente.isPresent()){
            var produto = produtoExistente.get();

            if (body.sku() != null && !body.sku().isEmpty()) {
                produto.setSku(body.sku());
            }

            if (body.nomeProduto() != null && !body.nomeProduto().isEmpty()) {
                produto.setNomeProduto(body.nomeProduto());
            }

            if (body.descricaoProduto() != null && !body.descricaoProduto().isEmpty()) {
                produto.setDescricaoProduto(body.descricaoProduto());
            }

            if (body.precoProduto() != null && body.precoProduto().compareTo(BigDecimal.ZERO) != 0) {
                produto.setPrecoProduto(body.precoProduto());
            }

            produtoRepository.save(produto);
        }
    }

    public void deletarProduto(UUID id) {
        produtoRepository.deleteById(id);
    }
}
