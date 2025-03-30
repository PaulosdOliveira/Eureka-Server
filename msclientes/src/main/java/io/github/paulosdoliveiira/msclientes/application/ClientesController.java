package io.github.paulosdoliveiira.msclientes.application;

import io.github.paulosdoliveiira.msclientes.application.representation.ClienteSaveRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("clientes")
public class ClientesController {

    //swagger-ui/index.html

    @Autowired
    private ClienteService service;

    @GetMapping
    public String status() {
        log.info("Aqui");
        return "Estou funcionando";
    }

    @PostMapping
    public ResponseEntity salvarCliente(@RequestBody ClienteSaveRequest cliente) {
        service.salvarCliente(cliente.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(cliente.getCpf()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity buscarCliente(@RequestParam("cpf") String cpf) {
        var cliente = service.buscarCliente(cpf);
        return cliente != null? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
    }
}
