package com.Loja.Loja.repositories;

import com.Loja.Loja.models.EmpresaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface EmpresaRepository extends JpaRepository<EmpresaModel, Long> {

}
