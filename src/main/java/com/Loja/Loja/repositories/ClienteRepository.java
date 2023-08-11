package com.Loja.Loja.repositories;

import com.Loja.Loja.models.ClienteModel;
import com.Loja.Loja.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

}
