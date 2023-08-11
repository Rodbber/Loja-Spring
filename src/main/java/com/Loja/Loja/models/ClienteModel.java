package com.Loja.Loja.models;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name="CLIENTES")
public class ClienteModel extends BaseEntiti {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@OneToMany(mappedBy = "cliente_model", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="empresa_id")
    private EmpresaModel empresa;

    private String nome;

    private String cpf;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EmpresaModel getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaModel empresa) {
        this.empresa = empresa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
