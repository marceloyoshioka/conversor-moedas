package com.pet.controller;

import com.pet.model.DadosCotacao;
import com.pet.model.ResultadoConversaoDto;
import com.pet.service.CotacaoService;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class CotacaoController {

	@Autowired
	private CotacaoService service;
	
	@GetMapping("/cotacao")
	public ResponseEntity<DadosCotacao>  obterCotacao() {
		return ResponseEntity.ok(service.obterCotacao());
		
	}
	
	@GetMapping("/converter")
	public ResponseEntity<ResultadoConversaoDto> converterReaisParaDolares(
			@RequestParam String daMoeda,
			@RequestParam String paraMoeda,
			@RequestParam BigDecimal valor
			) {
		return ResponseEntity.ok(service.converterReaisParaDolares(daMoeda, paraMoeda, valor));
	}
	
}
