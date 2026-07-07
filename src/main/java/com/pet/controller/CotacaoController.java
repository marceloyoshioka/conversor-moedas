package com.pet.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.model.DadosCotacao;
import com.pet.model.ResultadoConversaoDto;
import com.pet.service.ConsumoApiService;
import com.pet.service.ConverteDados;
import com.pet.service.CotacaoService;

import org.apache.catalina.connector.Response;
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
	public ResponseEntity<ResultadoConversaoDto> converterReaisParaDolares(@RequestParam Double valorEmReais) {
		return ResponseEntity.ok(service.converterReaisParaDolares(valorEmReais));
	}
	
}
