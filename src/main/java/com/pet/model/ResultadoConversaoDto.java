package com.pet.model;

public record ResultadoConversaoDto(
			Double valorOriginal,
		    String moedaOrigem,
		    String moedaDestino,
		    Double taxaCambio,
		    Double valorConvertido
		) {

}
