package com.Loja.Loja.dtos;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

public record VendaRecordsDto(UUID id,Long cliente_id, @NotNull UUID carrinho_id,
                              Double valor, @PositiveOrZero
Double acrecimo_desconto) {
}
