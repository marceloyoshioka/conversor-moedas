package com.pet.service;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class ConverteDados implements IConverteDados{

	private final ObjectMapper mapper = new ObjectMapper();
	
	@Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter o JSON para a classe: " + classe.getSimpleName(), e);
        }
    }
	
}
