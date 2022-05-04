package com.dio.santander.banklineapi.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dio.santander.banklineapi.dto.MovimentacaoDTO;
import com.dio.santander.banklineapi.model.Correntista;
import com.dio.santander.banklineapi.model.Movimentacao;
import com.dio.santander.banklineapi.model.MovimentacaoTipo;
import com.dio.santander.banklineapi.repository.CorrentistaRepository;
import com.dio.santander.banklineapi.repository.MovimentacaoRepository;

@Service
public class MovimentacaoService {
	
	@Autowired
	MovimentacaoRepository repository;

	@Autowired
	CorrentistaRepository correntistaRepository;

	public void save(MovimentacaoDTO dto) {
		Movimentacao movimentacao = new Movimentacao();
		Double valor = dto.getTipo() == MovimentacaoTipo.RECEITA ? dto.getValor() : dto.getValor() * -1;
		
		movimentacao.setDataHora(LocalDateTime.now());
		movimentacao.setDescricao(dto.getDescricao());
		movimentacao.setTipo(dto.getTipo());
		movimentacao.setIdConta(dto.getIdConta());
		movimentacao.setValor(valor);
		
		Correntista correntista = correntistaRepository.findById(dto.getIdConta()).orElse(null); // se não achar fica null
		if(correntista != null) {
			correntista.getConta().setSaldo(correntista.getConta().getSaldo() + valor);
			correntistaRepository.save(correntista);
		}
		
		repository.save(movimentacao);
	}
}
