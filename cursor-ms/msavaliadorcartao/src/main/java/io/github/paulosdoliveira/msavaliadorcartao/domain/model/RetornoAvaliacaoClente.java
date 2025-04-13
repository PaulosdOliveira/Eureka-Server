package io.github.paulosdoliveira.msavaliadorcartao.domain.model;

import java.util.List;

public class RetornoAvaliacaoClente {

private List<CartaoAprovado> cartoes;


    public RetornoAvaliacaoClente() {
    }

    public RetornoAvaliacaoClente(List<CartaoAprovado> cartoes) {
        this.cartoes = cartoes;
    }

    public List<CartaoAprovado> getCartoes() {
        return cartoes;
    }

    public void setCartoes(List<CartaoAprovado> cartoes) {
        this.cartoes = cartoes;
    }
}
