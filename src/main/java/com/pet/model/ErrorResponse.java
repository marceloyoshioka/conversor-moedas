package com.pet.model;

import java.time.LocalDateTime;

public record ErrorResponse(
			String mensagem,
			LocalDateTime dataHora,
			int status
		) {

}
