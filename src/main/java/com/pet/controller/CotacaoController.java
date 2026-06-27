package com.pet.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.model.DadosCotacao;
import com.pet.model.ResultadoConversaoDto;
import com.pet.service.ConsumoApiService;
import com.pet.service.ConverteDados;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class CotacaoController {

	@GetMapping("/cotacao")
	public DadosCotacao obterCotacao() {
		ConsumoApiService consumoApi = new ConsumoApiService();
        ConverteDados conversor = new ConverteDados();
        
        String url = "https://economia.awesomeapi.com.br/json/last/USD-BRL";
        String jsonPuro = consumoApi.obterDados(url);
        
        try {
            // 1. Usamos o ObjectMapper para ler o JSON como uma árvore de nós
            ObjectMapper mapper = new ObjectMapper();
            JsonNode raiz = mapper.readTree(jsonPuro);
            
            // 2. Pegamos o primeiro nó interno de forma dinâmica (que neste caso é o "USDBRL")
            JsonNode noInterno = raiz.elements().next();
            
            // 3. Convertemos apenas o JSON interno para texto
            String jsonTratado = noInterno.toString();
            
            // 4. Agora sim! O seu conversor recebe o JSON limpo e sem o "envelope"
            DadosCotacao dados = conversor.obterDados(jsonTratado, DadosCotacao.class);
            
            return dados;
            
        } catch (Exception e) {
            throw new RuntimeException("Erro ao tratar o nó do JSON da AwesomeAPI", e);
        }
		
	}
	
	@GetMapping("/converter")
	public ResultadoConversaoDto converterReaisParaDolares(@RequestParam Double valorEmReais) {
		
		ConsumoApiService consumoApi = new ConsumoApiService();
        ConverteDados conversor = new ConverteDados();
        
        String url = "https://economia.awesomeapi.com.br/json/last/USD-BRL";
        String jsonPuro = consumoApi.obterDados(url);
		
        try {
            // 1. Extrai o miolo do JSON ("USDBRL")
            ObjectMapper mapper = new ObjectMapper();
            JsonNode raiz = mapper.readTree(jsonPuro);
            JsonNode noInterno = raiz.elements().next();
            
            DadosCotacao dados = conversor.obterDados(noInterno.toString(), DadosCotacao.class);
            
            // 2. Transforma o valor do BID (que vem como String) em Double para fazermos conta
            Double taxaBid = Double.parseDouble(dados.valorCompra());
            
           // 3. Regra de Negócio: Para converter de Real para Dólar, nós DIVIDIMOS
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
	
}
