package com.Loja.Loja.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Entity
@Table(name="CARRINHOS")
public class CarrinhoModel extends BaseEntiti {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="empresa_id", nullable=false)
    private EmpresaModel empresa;

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<CarrinhoProdutoModel> carrinhoProdutos;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public EmpresaModel getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaModel empresa) {
        this.empresa = empresa;
    }
}
