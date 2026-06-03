package com.estudos.gerenciador_armazenamento.controller;

import com.estudos.gerenciador_armazenamento.domain.Movimentacao;
import com.estudos.gerenciador_armazenamento.dto.movimentacao.CreateMovimentacaoDto;
import com.estudos.gerenciador_armazenamento.dto.movimentacao.UpdateMovimentacaoDto;
import com.estudos.gerenciador_armazenamento.service.EntradaService;
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

@RestController
@RequestMapping("/v1/entrada")
@Tag(name = "Entradas", description = "APIs para gerenciar entradas de produtos no armazenamento")
public class EntradaController {

    @Autowired
    private EntradaService entradaService;

    @PostMapping
    @Operation(summary = "Registrar nova entrada", description = "Registra uma nova entrada de produto no armazenamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Entrada registrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<Void> inserirEntrada(@RequestBody CreateMovimentacaoDto body){
        var entradaId = entradaService.criarEntrada(body);
        return ResponseEntity.created(URI.create("/v1/entrada/" + entradaId)).build();
    }

    @GetMapping("/sku/{sku}")
    @Operation(summary = "Buscar entradas por SKU", description = "Busca todas as entradas de um produto específico pelo SKU")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entradas encontradas", content = @Content(schema = @Schema(implementation = Movimentacao.class))),
            @ApiResponse(responseCode = "404", description = "Nenhuma entrada encontrada")
    })
    public ResponseEntity<List<Movimentacao>> buscarEntradasPorSku(
            @Parameter(description = "SKU do produto", example = "SKU-001")
            @PathVariable String sku){
        var entradaEncontrada = entradaService.buscarEntradasPorSku(sku);

        if(entradaEncontrada.isPresent()) { return ResponseEntity.ok(entradaEncontrada.get()); }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Buscar entrada por ID", description = "Busca uma entrada específica pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Entrada encontrada", content = @Content(schema = @Schema(implementation = Movimentacao.class))),
            @ApiResponse(responseCode = "404", description = "Entrada não encontrada")
    })
    public ResponseEntity<Movimentacao> buscarEntradaPorId(
            @Parameter(description = "ID da entrada", example = "1")
            @PathVariable Integer id){
        var entradaEncontrada = entradaService.buscarEntradaPorId(id);

        if(entradaEncontrada.isPresent()) { return ResponseEntity.ok(entradaEncontrada.get()); }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    @Operation(summary = "Listar todas as entradas", description = "Retorna uma lista de todas as entradas registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de entradas", content = @Content(schema = @Schema(implementation = Movimentacao.class))),
            @ApiResponse(responseCode = "404", description = "Nenhuma entrada encontrada")
    })
    public ResponseEntity<List<Movimentacao>> listarEntradas(){
        var entradas = entradaService.listarMovimentacoes();

        if(entradas.isEmpty()){ return ResponseEntity.notFound().build(); }

        return ResponseEntity.ok(entradas);
    }

    @PutMapping("/id/{id}")
    @Operation(summary = "Atualizar uma entrada", description = "Atualiza os dados de uma entrada existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Entrada atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Entrada não encontrada")
    })
    public ResponseEntity<Void> atualizarEntrada(
            @Parameter(description = "ID da entrada", example = "1")
            @PathVariable Integer id,
            @RequestBody UpdateMovimentacaoDto body){
        entradaService.atualizarMovimentacao(id, body);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/id/{id}")
    @Operation(summary = "Deletar uma entrada", description = "Remove uma entrada do registro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Entrada deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Entrada não encontrada")
    })
    public ResponseEntity<Void> deletarEntradaPorId(
            @Parameter(description = "ID da entrada", example = "1")
            @PathVariable Integer id){
        entradaService.deletarEntrada(id);
        return ResponseEntity.noContent().build();
    }
}
