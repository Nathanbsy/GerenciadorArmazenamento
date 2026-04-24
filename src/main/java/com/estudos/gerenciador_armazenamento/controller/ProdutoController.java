package com.estudos.gerenciador_armazenamento.controller;

import com.estudos.gerenciador_armazenamento.domain.Produto;
import com.estudos.gerenciador_armazenamento.dto.CreateProdutoDto;
import com.estudos.gerenciador_armazenamento.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/v1/produtos")
public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Produto> inserirProduto(@RequestBody CreateProdutoDto body){
        UUID produtoId = produtoService.criarProduto(body);
        return ResponseEntity.created(URI.create("/v1/produtos" + produtoId.toString())).build();
    }

    @GetMapping("/{sku}")
    public ResponseEntity<Produto> buscarProdutoPorSku(@PathVariable String sku){
        return null;
    }
}
