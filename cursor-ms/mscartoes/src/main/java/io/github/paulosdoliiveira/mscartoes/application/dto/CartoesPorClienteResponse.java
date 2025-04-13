package io.github.paulosdoliiveira.mscartoes.application.dto;

import io.github.paulosdoliiveira.mscartoes.model.ClienteCartao;

import java.math.BigDecimal;

public class CartoesPorClienteResponse {
    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;

    public CartoesPorClienteResponse() {
    }

    public CartoesPorClienteResponse(String nome, String bandeira, BigDecimal limteLiberado) {
        this.nome = nome;
        this.bandeira = bandeira;
        this.limiteLiberado = limteLiberado;
    }

    public static CartoesPorClienteResponse fromModel(ClienteCartao model) {
        return new CartoesPorClienteResponse(
                model.getCartao().getNome(),
                model.getCartao().getBandeira().toString(),
                model.getLimite()
        );

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

    public BigDecimal getLimiteLiberado() {
        return limiteLiberado;
    }

    public void setLimiteLiberado(BigDecimal limteLiberado) {
        this.limiteLiberado = limteLiberado;
    }
}
