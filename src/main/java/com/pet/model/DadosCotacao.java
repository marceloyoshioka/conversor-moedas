package com.pet.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosCotacao(
			@JsonAlias("code") String moedaOrigem,
			@JsonAlias("codein") String moedaDestino,
			@JsonAlias("bid") String valorCompra,
			@JsonAlias("pctChange") String porcentagemVariacao
		) {

}
