package com.pet.model;

import java.math.BigDecimal;

public record ResultadoConversaoDto(
			Double valorOriginal,
		    String moedaOrigem,
		    String moedaDestino,
		    Double taxaCambio,
		    Double valorConvertido
		) {

}
