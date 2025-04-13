package io.github.paulosdoliveira.msavaliadorcartao.domain.model;

import java.math.BigDecimal;

public class Cartao {

    private Long id;
    private String nome;
    private String bandeira;
    private BigDecimal renda;
    private BigDecimal limiteCartao;


    public Cartao() {
    }

    public Cartao(Long id, String nome, String bandeira, BigDecimal renda, BigDecimal limiteCartao) {
        this.id = id;
        this.nome = nome;
        this.bandeira = bandeira;
        this.renda = renda;
        this.limiteCartao = limiteCartao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public BigDecimal getRenda() {
        return renda;
    }

    public void setRenda(BigDecimal renda) {
        this.renda = renda;
    }

    public BigDecimal getLimiteCartao() {
        return limiteCartao;
    }

    public void setLimiteCartao(BigDecimal limiteCartao) {
        this.limiteCartao = limiteCartao;
    }
}
