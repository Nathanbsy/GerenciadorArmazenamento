package com.estudos.gerenciador_armazenamento.controller;

import com.estudos.gerenciador_armazenamento.domain.Produto;
import com.estudos.gerenciador_armazenamento.dto.produto.CreateProdutoDto;
import com.estudos.gerenciador_armazenamento.dto.produto.UpdateProdutoDto;
import com.estudos.gerenciador_armazenamento.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/produtos")
@Tag(name = "Produtos", description = "APIs para gerenciar produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    @Operation(summary = "Criar um novo produto", description = "Cria um novo produto no armazenamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<Produto> inserirProduto(@RequestBody CreateProdutoDto body){
        var produtoId = produtoService.criarProduto(body);
        return ResponseEntity.created(URI.create("/v1/produtos/" + produtoId.toString())).build();
    }

    @GetMapping("/sku/{sku}")
    @Operation(summary = "Buscar produto por SKU", description = "Busca um produto específico pelo seu SKU")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado", content = @Content(schema = @Schema(implementation = Produto.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<Produto> buscarProdutoPorSku(
            @Parameter(description = "SKU do produto", example = "SKU-001")
            @PathVariable String sku){
        var produtoEncontrado = produtoService.buscarProdutoPorSku(sku);

        if(produtoEncontrado.isPresent()) { return ResponseEntity.ok(produtoEncontrado.get()); }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Buscar produto por ID", description = "Busca um produto específico pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado", content = @Content(schema = @Schema(implementation = Produto.class))),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<Produto> buscarProdutoPorId(
            @Parameter(description = "ID do produto", example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable UUID id){
        var produtoEncontrado = produtoService.buscarProdutoPorId(id);

        if(produtoEncontrado.isPresent()) { return ResponseEntity.ok(produtoEncontrado.get()); }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    @Operation(summary = "Listar todos os produtos", description = "Retorna uma lista de todos os produtos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos", content = @Content(schema = @Schema(implementation = Produto.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum produto encontrado")
    })
    public ResponseEntity<List<Produto>> listarProdutos(){
        var produtos = produtoService.listarProdutos();

        if(produtos.isEmpty()){ return ResponseEntity.notFound().build(); }

        return ResponseEntity.ok(produtos);
    }

    @PutMapping("/id/{id}")
    @Operation(summary = "Atualizar um produto", description = "Atualiza os dados de um produto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<Void> atualizarProduto(
            @Parameter(description = "ID do produto", example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable UUID id,
            @RequestBody UpdateProdutoDto body){
        produtoService.atualizarProduto(id, body);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/id/{id}")
    @Operation(summary = "Deletar um produto", description = "Remove um produto do armazenamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<Void> deletarProdutoPorId(
            @Parameter(description = "ID do produto", example = "550e8400-e29b-41d4-a716-446655440000")
            @PathVariable UUID id){
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}
