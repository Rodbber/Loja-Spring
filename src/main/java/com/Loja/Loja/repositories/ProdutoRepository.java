package com.Loja.Loja.repositories;

import com.Loja.Loja.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, UUID> {

}
