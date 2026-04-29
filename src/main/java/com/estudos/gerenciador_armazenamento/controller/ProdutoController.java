package com.estudos.gerenciador_armazenamento.controller;

import com.estudos.gerenciador_armazenamento.domain.Produto;
import com.estudos.gerenciador_armazenamento.dto.CreateProdutoDto;
import com.estudos.gerenciador_armazenamento.dto.UpdateProdutoDto;
import com.estudos.gerenciador_armazenamento.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
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
        var produtoId = produtoService.criarProduto(body);
        return ResponseEntity.created(URI.create("/v1/produtos" + produtoId.toString())).build();
    }

    @GetMapping("/sku/{sku}")
    public ResponseEntity<Produto> buscarProdutoPorSku(@PathVariable String sku){
        var produtoEncontrado = produtoService.buscarProdutoPorSku(sku);

        if(produtoEncontrado.isPresent()) { return ResponseEntity.ok(produtoEncontrado.get()); }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable UUID id){
        var produtoEncontrado = produtoService.buscarProdutoPorId(id);

        if(produtoEncontrado.isPresent()) { return ResponseEntity.ok(produtoEncontrado.get()); }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos(){
        var produtos = produtoService.listarProdutos();

        if(produtos.isEmpty()){ return ResponseEntity.notFound().build(); }

        return ResponseEntity.ok(produtos);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Void> atualizarProduto(@PathVariable UUID id, @RequestBody UpdateProdutoDto body){
        produtoService.atualizarProduto(id, body);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deletarProdutoPorId(@PathVariable UUID id){
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}
