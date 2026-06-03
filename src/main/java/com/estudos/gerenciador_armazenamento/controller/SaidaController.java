package com.estudos.gerenciador_armazenamento.controller;

import com.estudos.gerenciador_armazenamento.domain.Movimentacao;
import com.estudos.gerenciador_armazenamento.dto.movimentacao.CreateMovimentacaoDto;
import com.estudos.gerenciador_armazenamento.dto.movimentacao.UpdateMovimentacaoDto;
import com.estudos.gerenciador_armazenamento.service.SaidaService;
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
@RequestMapping("/v1/saida")
@Tag(name = "Saídas", description = "APIs para gerenciar saídas de produtos do armazenamento")
public class SaidaController {

    @Autowired
    private SaidaService saidaService;

    @PostMapping
    @Operation(summary = "Registrar nova saída", description = "Registra uma nova saída de produto do armazenamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Saída registrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    public ResponseEntity<Void> inserirSaida(@RequestBody CreateMovimentacaoDto body){
        var saidaId = saidaService.criarSaida(body);
        return ResponseEntity.created(URI.create("/v1/saida/" + saidaId)).build();
    }

    @GetMapping("/sku/{sku}")
    @Operation(summary = "Buscar saídas por SKU", description = "Busca todas as saídas de um produto específico pelo SKU")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saídas encontradas", content = @Content(schema = @Schema(implementation = Movimentacao.class))),
            @ApiResponse(responseCode = "404", description = "Nenhuma saída encontrada")
    })
    public ResponseEntity<List<Movimentacao>> buscarSaidasPorSku(
            @Parameter(description = "SKU do produto", example = "SKU-001")
            @PathVariable String sku){
        var saidaEncontrada = saidaService.buscarSaidasPorSku(sku);

        if(saidaEncontrada.isPresent()) { return ResponseEntity.ok(saidaEncontrada.get()); }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Buscar saída por ID", description = "Busca uma saída específica pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saída encontrada", content = @Content(schema = @Schema(implementation = Movimentacao.class))),
            @ApiResponse(responseCode = "404", description = "Saída não encontrada")
    })
    public ResponseEntity<Movimentacao> buscarSaidaPorId(
            @Parameter(description = "ID da saída", example = "1")
            @PathVariable Integer id){
        var saidaEncontrada = saidaService.buscarSaidaPorId(id);

        if(saidaEncontrada.isPresent()) { return ResponseEntity.ok(saidaEncontrada.get()); }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    @Operation(summary = "Listar todas as saídas", description = "Retorna uma lista de todas as saídas registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de saídas", content = @Content(schema = @Schema(implementation = Movimentacao.class))),
            @ApiResponse(responseCode = "404", description = "Nenhuma saída encontrada")
    })
    public ResponseEntity<List<Movimentacao>> listarSaidas(){
        var saidas = saidaService.listarSaidas();

        if(saidas.isEmpty()){ return ResponseEntity.notFound().build(); }

        return ResponseEntity.ok(saidas);
    }

    @PutMapping("/id/{id}")
    @Operation(summary = "Atualizar uma saída", description = "Atualiza os dados de uma saída existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Saída atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Saída não encontrada")
    })
    public ResponseEntity<Void> atualizarSaida(
            @Parameter(description = "ID da saída", example = "1")
            @PathVariable Integer id,
            @RequestBody UpdateMovimentacaoDto body){
        saidaService.atualizarSaida(id, body);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/id/{id}")
    @Operation(summary = "Deletar uma saída", description = "Remove uma saída do registro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Saída deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Saída não encontrada")
    })
    public ResponseEntity<Void> deletarSaidaPorId(
            @Parameter(description = "ID da saída", example = "1")
            @PathVariable Integer id){
        saidaService.deletarSaida(id);
        return ResponseEntity.noContent().build();
    }
}
