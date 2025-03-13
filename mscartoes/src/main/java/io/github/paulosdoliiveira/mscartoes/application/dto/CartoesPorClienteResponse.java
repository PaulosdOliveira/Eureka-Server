package io.github.paulosdoliiveira.mscartoes.application.dto;

import io.github.paulosdoliiveira.mscartoes.model.ClienteCartao;

import java.math.BigDecimal;

public class CartoesPorClienteResponse {
    private String nome;
    private String bandeira;
    private BigDecimal limteLiberado;

    public CartoesPorClienteResponse() {
    }

    public CartoesPorClienteResponse(String nome, String bandeira, BigDecimal limteLiberado) {
        this.nome = nome;
        this.bandeira = bandeira;
        this.limteLiberado = limteLiberado;
    }

    public static CartoesPorClienteResponse fromModel(ClienteCartao model) {
        return new CartoesPorClienteResponse(
                model.getCartao().getNome(),
                model.getCartao().getBandeira().toString(),
                model.getCartao().getLimiteCartao()
        );
    }
}
