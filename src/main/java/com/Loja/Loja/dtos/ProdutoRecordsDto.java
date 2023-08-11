package com.Loja.Loja.dtos;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

public record ProdutoRecordsDto(UUID id, @NotBlank String nome, @Positive Double valor, @PositiveOrZero Integer quantidade, @Positive Long empresa_id) {
}
