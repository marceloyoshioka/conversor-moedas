package com.pet.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

@Service
public class ConsumoApiService {
	
	public String obterDados(String endereco) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(endereco))
				.build();
		
		try {
			HttpResponse<String> response = client
					.send(request, HttpResponse.BodyHandlers.ofString());
			return response.body();
		}catch (IOException | InterruptedException e) {
            throw new RuntimeException("Erro ao conectar com a API externa: " + e.getMessage(), e);
        }
	}
}
