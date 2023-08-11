package com.Loja.Loja.repositories;

import com.Loja.Loja.models.ClienteModel;
import com.Loja.Loja.models.VendaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface VendaRepository extends JpaRepository<VendaModel, Long> {

}
