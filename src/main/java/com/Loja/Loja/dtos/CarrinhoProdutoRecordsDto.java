package com.Loja.Loja.dtos;
import jakarta.validation.constraints.*;

import java.math.BigInteger;
import java.util.UUID;

public record CarrinhoProdutoRecordsDto(UUID id, @NotNull UUID carrinho_id, @NotNull UUID produto_id, @Positive Integer quantidade) {
}
