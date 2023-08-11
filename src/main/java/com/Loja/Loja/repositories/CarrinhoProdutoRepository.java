package com.Loja.Loja.repositories;

import com.Loja.Loja.models.CarrinhoProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CarrinhoProdutoRepository extends JpaRepository<CarrinhoProdutoModel, UUID> {
    List<CarrinhoProdutoModel> findAllByCarrinho_Id(UUID carrinhoId);
}
