package com.Loja.Loja.dtos;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

public record CarrinhoRecordsDto(UUID id, @Positive Long empresa_id) {
}
