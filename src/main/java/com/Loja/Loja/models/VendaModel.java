package com.Loja.Loja.models;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

@Entity
@Table(name = "vendas")
public class VendaModel extends BaseEntiti {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "decimal(10,2) default 0")
    private Double valor;

    @Column(columnDefinition = "decimal(10,2) default 0")
    private Double acrescimo_desconto;

    @ManyToOne
    @JoinColumn(name="cliente_id", unique = false)
    private ClienteModel cliente;

    @ManyToOne
    @JoinColumn(name="carrinho_id", unique = false)
    private CarrinhoModel carrinho;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getAcrescimo_desconto() {
        return acrescimo_desconto;
    }

    public void setAcrescimo_desconto(Double acrescimo_desconto) {
        this.acrescimo_desconto = acrescimo_desconto;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public CarrinhoModel getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(CarrinhoModel carrinho) {
        this.carrinho = carrinho;
    }
}
