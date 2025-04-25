package io.github.paulosdoliveira.msavaliadorcartao.domain.model;

import java.util.List;

public class SituacaoCliente {

    private  DadosCliente dadosCliente;
    private List<CartaoCliente> cartao;

    public SituacaoCliente() {
    }

    public SituacaoCliente(DadosCliente dadosCliente, List<CartaoCliente> cartao) {
        this.dadosCliente = dadosCliente;
        this.cartao = cartao;
    }

    public DadosCliente getDadosCliente() {
        return dadosCliente;
    }

    public void setDadosCliente(DadosCliente dadosCliente) {
        this.dadosCliente = dadosCliente;
    }

    public List<CartaoCliente> getCartao() {
        return cartao;
    }

    public void setCartao(List<CartaoCliente> cartao) {
        this.cartao = cartao;
    }
}
