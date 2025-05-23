package io.github.paulosdoliveira.msavaliadorcartao.infra.clients;

import io.github.paulosdoliveira.msavaliadorcartao.domain.model.DadosCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "msclientes", path = "/clientes")
public interface ClienteResourceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<DadosCliente> buscarCliente(@RequestParam("cpf") String cpf);
}
