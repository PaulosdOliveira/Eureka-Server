package io.github.paulosdoliveira.msavaliadorcartao.domain.model;

import java.math.BigDecimal;

public class CartaoAprovado {

    private String cartao;
    private String bandera;
    private BigDecimal limiteAprovado;

    public String getCartao() {
        return cartao;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

    public String getBandera() {
        return bandera;
    }

    public void setBandera(String bandera) {
        this.bandera = bandera;
    }

    public BigDecimal getLimiteAprovado() {
        return limiteAprovado;
    }

    public void setLimiteAprovado(BigDecimal limiteAprovado) {
        this.limiteAprovado = limiteAprovado;
    }
}
