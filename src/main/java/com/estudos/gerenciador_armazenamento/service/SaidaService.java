package com.estudos.gerenciador_armazenamento.service;

import com.estudos.gerenciador_armazenamento.domain.Movimentacao;
import com.estudos.gerenciador_armazenamento.domain.Produto;
import com.estudos.gerenciador_armazenamento.dto.movimentacao.CreateMovimentacaoDto;
import com.estudos.gerenciador_armazenamento.dto.movimentacao.UpdateMovimentacaoDto;
import com.estudos.gerenciador_armazenamento.repository.SaidaRepository;
import com.estudos.gerenciador_armazenamento.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SaidaService {
    private ProdutoRepository produtoRepository;
    private SaidaRepository saidaRepository;

    public SaidaService(SaidaRepository saidaRepository, ProdutoRepository produtoRepository) {
        this.saidaRepository = saidaRepository;
        this.produtoRepository = produtoRepository;
    }

    public Integer criarSaida(CreateMovimentacaoDto saidaDto) {
        Optional<Produto> produto = produtoRepository.findById(saidaDto.produtoId());
        
        if(produto.isEmpty()) {
            throw new RuntimeException("Produto não encontrado!");
        }

        Movimentacao saida = new Movimentacao();

        saida.setCliente(saidaDto.cliente());
        saida.setProduto(produto.get());
        saida.setQuantidade(saidaDto.quantidade());
        saida.setTipo("SAIDA");
        saida.setDataMovimentacao(saidaDto.dataMovimentacao() != null ? saidaDto.dataMovimentacao() : LocalDateTime.now());

        Movimentacao saidaSalva = saidaRepository.save(saida);

        return saidaSalva.getId();
    }

    public Optional<Movimentacao> buscarSaidaPorId(Integer id) {
        return saidaRepository.findById(id);
    }

    public Optional<List<Movimentacao>> buscarSaidasPorSku(String sku) {
        List<Movimentacao> saidas = saidaRepository.findByProdutoSku(sku);
        if(saidas.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(saidas);
    }

    public List<Movimentacao> listarSaidas() {
        return saidaRepository.findAll();
    }

    public void atualizarSaida(Integer id, UpdateMovimentacaoDto saidaDto) {

        var movOpt = saidaRepository.findById(id);

        if (movOpt.isPresent()) {
            var saida = movOpt.get();

            if (saidaDto.cliente() != null && !saidaDto.cliente().isEmpty()) {
                saida.setCliente(saidaDto.cliente());
            }

            if (saidaDto.quantidade() != 0) {
                saida.setQuantidade(saidaDto.quantidade());
            }

            if (saidaDto.dataMovimentacao() != null) {
                saida.setDataMovimentacao(saidaDto.dataMovimentacao());
            }

            saidaRepository.save(saida);
        }
    }

    public void deletarSaida(Integer id) {
        saidaRepository.deleteById(id);
    }
}
