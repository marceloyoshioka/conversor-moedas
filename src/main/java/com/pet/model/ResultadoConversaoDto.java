package com.pet.model;

import java.math.BigDecimal;

public record ResultadoConversaoDto(
			BigDecimal valorOriginal,
		    String moedaOrigem,
		    String moedaDestino,
		    BigDecimal taxaCambio,
		    BigDecimal valorConvertido
		) {

}
