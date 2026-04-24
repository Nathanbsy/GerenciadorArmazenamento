package com.estudos.gerenciador_armazenamento.controller;

import com.estudos.gerenciador_armazenamento.domain.Produto;
import com.estudos.gerenciador_armazenamento.dto.CreateProdutoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProdutoController {

    @PostMapping
    public ResponseEntity<Produto> inserirProduto(@RequestBody CreateProdutoDto body){
        return null;
    }

    @GetMapping("/{sku}")
    public ResponseEntity<Produto> buscarProdutoPorSku(@PathVariable String sku){
        return null;
    }
}
