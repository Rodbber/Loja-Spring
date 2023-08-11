package com.Loja.Loja.dtos;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigInteger;

public record EmpresaRecordsDto(Long id, @NotBlank String nome, @NotBlank @Size(min = 14, max = 14) String cnpj) {
}
