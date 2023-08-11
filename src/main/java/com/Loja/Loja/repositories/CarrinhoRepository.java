package com.Loja.Loja.repositories;

import com.Loja.Loja.models.CarrinhoModel;
import com.Loja.Loja.models.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.UUID;

public interface CarrinhoRepository extends JpaRepository<CarrinhoModel, UUID> {

}
