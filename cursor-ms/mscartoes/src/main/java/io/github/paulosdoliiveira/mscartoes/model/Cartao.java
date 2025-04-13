package io.github.paulosdoliiveira.mscartoes.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @Enumerated(EnumType.STRING)
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limiteCartao;

    public Cartao() {
    }

    public Cartao(String nome, BandeiraCartao bandeira, BigDecimal renda, BigDecimal limiteCartao) {
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

    public BandeiraCartao getBandeira() {
        return bandeira;
    }

    public void setBandeira(BandeiraCartao bandeira) {
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

