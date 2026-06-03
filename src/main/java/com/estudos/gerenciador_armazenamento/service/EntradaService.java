package com.estudos.gerenciador_armazenamento.service;

import com.estudos.gerenciador_armazenamento.domain.Movimentacao;
import com.estudos.gerenciador_armazenamento.domain.Produto;
import com.estudos.gerenciador_armazenamento.dto.movimentacao.CreateMovimentacaoDto;
import com.estudos.gerenciador_armazenamento.dto.movimentacao.UpdateMovimentacaoDto;
import com.estudos.gerenciador_armazenamento.repository.EntradaRepository;
import com.estudos.gerenciador_armazenamento.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EntradaService {
    private ProdutoRepository produtoRepository;
    private EntradaRepository entradaRepository;

    public EntradaService(EntradaRepository entradaRepository, ProdutoRepository produtoRepository) {
        this.entradaRepository = entradaRepository;
        this.produtoRepository = produtoRepository;
    }

    public Integer criarEntrada(CreateMovimentacaoDto entradaDto) {
        Optional<Produto> produto = produtoRepository.findById(entradaDto.produtoId());
        
        if(produto.isEmpty()) {
            throw new RuntimeException("Produto não encontrado!");
        }

        Movimentacao entrada = new Movimentacao();

        entrada.setCliente(entradaDto.cliente());
        entrada.setProduto(produto.get());
        entrada.setQuantidade(entradaDto.quantidade());
        entrada.setTipo("ENTRADA");
        entrada.setDataMovimentacao(entradaDto.dataMovimentacao() != null ? entradaDto.dataMovimentacao() : LocalDateTime.now());

        Movimentacao entradaSalva = entradaRepository.save(entrada);

        return entradaSalva.getId();
    }

    public Optional<Movimentacao> buscarEntradaPorId(Integer id) {
        return entradaRepository.findById(id);
    }

    public Optional<List<Movimentacao>> buscarEntradasPorSku(String sku) {
        List<Movimentacao> entradas = entradaRepository.findByProdutoSku(sku);
        if(entradas.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(entradas);
    }

    public List<Movimentacao> listarMovimentacoes() {
        return entradaRepository.findAll();
    }

    public void atualizarMovimentacao(Integer id, UpdateMovimentacaoDto entradaDto) {

        var movOpt = entradaRepository.findById(id);

        if (movOpt.isPresent()) {
            var entrada = movOpt.get();

            if (entradaDto.cliente() != null && !entradaDto.cliente().isEmpty()) {
                entrada.setCliente(entradaDto.cliente());
            }

            if (entradaDto.quantidade() != 0) {
                entrada.setQuantidade(entradaDto.quantidade());
            }

            if (entradaDto.dataMovimentacao() != null) {
                entrada.setDataMovimentacao(entradaDto.dataMovimentacao());
            }

            entradaRepository.save(entrada);
        }
    }

    public void deletarEntrada(Integer id) {
        entradaRepository.deleteById(id);
    }
}
