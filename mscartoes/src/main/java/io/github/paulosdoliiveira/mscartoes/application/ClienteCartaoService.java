package io.github.paulosdoliiveira.mscartoes.application;

import io.github.paulosdoliiveira.mscartoes.infra.repository.ClienteCartaoRepository;
import io.github.paulosdoliiveira.mscartoes.model.ClienteCartao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteCartaoService {

    @Autowired
    private ClienteCartaoRepository repository;


    public List<ClienteCartao> getByCpf(String cpf){
        return repository.findByCpf(cpf);
    }

}
