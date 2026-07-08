package com.pet.service;

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
	
	private static final String URL = "https://economia.awesomeapi.com.br/json/last/USD-BRL";
	
	public DadosCotacao obterCotacao() {

		return buscarETratarDadosDaApi();
	}
	
	public ResultadoConversaoDto converterReaisParaDolares(Double valorEmReais) {
		
		DadosCotacao dados = buscarETratarDadosDaApi();
		
        try {
            
           Double taxaBid = Double.parseDouble(dados.valorCompra());
            
           Double valorConvertidoDolar = valorEmReais / taxaBid;
           
           return new ResultadoConversaoDto(
	        		   valorEmReais, 
	        		   "BRL", 
	        		   "USD", 
	        		   taxaBid, 
	        		   valorConvertidoDolar
        		   );
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao processar a conversão monetária", e);
        }
	
	}
	
	private DadosCotacao buscarETratarDadosDaApi() {
		
		String jsonPuro = consumoApi.obterDados(URL);
        
        try {
			JsonNode raiz = mapper.readTree(jsonPuro);
            JsonNode noInterno = raiz.elements().next();
            return conversor.obterDados(noInterno.toString(), DadosCotacao.class);
		} catch (Exception e) {
			throw new RuntimeException("Erro ao processar dados da AwesomeAPI", e);
		}
        
	}
	
}
