package com.pet.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.model.DadosCotacao;
import com.pet.model.ResultadoConversaoDto;

@Service
public class CotacaoService {

	@Autowired
	private ConsumoApiService consumoApi;
	
	@Autowired
	private ConverteDados conversor;
    
	private ObjectMapper mapper = new ObjectMapper();
	
	private static final String URL = "https://economia.awesomeapi.com.br/json/last/";
	
	public DadosCotacao obterCotacao() {

		return buscarETratarDadosDaApi("USD","BRL");
	}
	
	public ResultadoConversaoDto converterReaisParaDolares(String daMoeda, String paraMoeda, BigDecimal valor) {
		
		DadosCotacao dados = buscarETratarDadosDaApi(daMoeda, paraMoeda);
		
        try {
            
           BigDecimal taxaBid = new BigDecimal(dados.valorCompra());
           BigDecimal valorBruto = valor.multiply(taxaBid);
           
           BigDecimal valorFinalArredondado = valorBruto.setScale(4, RoundingMode.HALF_EVEN);
           
           BigDecimal taxaBidFormatada = taxaBid.setScale(4, RoundingMode.HALF_EVEN);
           
           return new ResultadoConversaoDto(
	        		   valor, 
	        		   daMoeda, 
	        		   paraMoeda, 
	        		   taxaBidFormatada, 
	        		   valorFinalArredondado
        		   );
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar a conversão monetária", e);
        }
	
	}
	
	private DadosCotacao buscarETratarDadosDaApi(String daMoeda, String paraMoeda) {
		
		String jsonPuro = consumoApi.obterDados(URL+daMoeda+"-"+paraMoeda);
        
        try {
			JsonNode raiz = mapper.readTree(jsonPuro);
            JsonNode noInterno = raiz.elements().next();
            return conversor.obterDados(noInterno.toString(), DadosCotacao.class);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao processar dados da AwesomeAPI", e);
		}
        
	}
	
}
