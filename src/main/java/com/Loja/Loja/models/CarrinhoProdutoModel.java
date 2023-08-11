package com.Loja.Loja.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name="CARRINHO_PRODUTOS")
public class CarrinhoProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Integer quantidade;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="carrinho_id", nullable = false)
    private CarrinhoModel carrinho;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="produto_id", nullable = false)
    private ProdutoModel produto;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public CarrinhoModel getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(CarrinhoModel carrinho) {
        this.carrinho = carrinho;
    }

    public ProdutoModel getProduto() {
        return produto;
    }

    public void setProduto(ProdutoModel produto) {
        this.produto = produto;
    }
}
