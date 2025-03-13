package io.github.paulosdoliiveira.mscartoes.application;

import io.github.paulosdoliiveira.mscartoes.infra.repository.CartaoRepository;
import io.github.paulosdoliiveira.mscartoes.model.Cartao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository repository;


    public void salvar(Cartao cartao) {
        repository.save(cartao);
    }

    public List<Cartao> buscarCartao(Long renda) {
        var RendaBigDecimal = BigDecimal.valueOf(renda);
        return repository.findByRendaLessThanEqual(RendaBigDecimal);
    }

}
