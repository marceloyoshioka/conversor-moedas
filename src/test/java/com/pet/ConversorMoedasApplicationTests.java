package com.pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pet.model.ResultadoConversaoDto;
import com.pet.service.CotacaoService;

@SpringBootTest
class ConversorMoedasApplicationTests {

	@Autowired
	private CotacaoService service;
	
	@Test
	void deveSerDiferenteDeNull() {
		ResultadoConversaoDto dto =	service.converterReaisParaDolares(1D);
		assertNotNull(dto.valorConvertido());
	}

	@Test 
	void deveSerMaiorQue0(){
		ResultadoConversaoDto dto =	service.converterReaisParaDolares(1D);
		assertTrue(dto.valorConvertido() > 0);
	}
	
	@Test
	void deveGarantirQueCalculoMatematicoEstaCorreto() {
		ResultadoConversaoDto dto =	service.converterReaisParaDolares(1D);
		Double provaReal = dto.valorConvertido() * dto.taxaCambio();
		assertEquals(1.0, provaReal, 0.01);
	}
}
