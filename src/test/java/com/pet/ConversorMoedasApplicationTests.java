package com.pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

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
		ResultadoConversaoDto dto =	service.converterReaisParaDolares("BRL","USD",new BigDecimal(1));
		assertNotNull(dto.valorConvertido());
	}

	
	@Test 
	void deveSerMaiorQue0(){
		ResultadoConversaoDto dto =	service.converterReaisParaDolares("BRL","USD",new BigDecimal(1));
		assertTrue(dto.valorConvertido().compareTo(BigDecimal.ZERO) > 0);
	}
	

}
