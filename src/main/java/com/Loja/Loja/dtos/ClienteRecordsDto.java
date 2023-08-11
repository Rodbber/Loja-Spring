package com.Loja.Loja.dtos;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

public record ClienteRecordsDto(Long id, @NotBlank String nome, @NotBlank @Size(min = 11, max = 11) String cpf, @Positive Long empresa_id) {
}
