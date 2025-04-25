package io.github.paulosdoliveiira.msclientes.application;

import io.github.paulosdoliveiira.msclientes.domain.Cliente;
import io.github.paulosdoliveiira.msclientes.infra.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente salvarCliente(Cliente cliente) {
        return repository.save(cliente);
    }

    public Cliente buscarCliente(String cpf){
        return repository.findByCpf(cpf).orElse(null);
    }
}
