package io.github.paulosdoliiveira.mscartoes.application;

import io.github.paulosdoliiveira.mscartoes.application.dto.CartoesPorClienteResponse;
import io.github.paulosdoliiveira.mscartoes.model.Cartao;
import io.github.paulosdoliiveira.mscartoes.model.CartaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cartoes")
public class CartaoController {

    @Autowired
    private CartaoService service;

    @Autowired
    private ClienteCartaoService clienteCartaoService;

    @GetMapping
    public String status() {
        return "Estou funcionado";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrar(@RequestBody CartaoDTO dados) {
        service.salvar(dados.toModel());
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAte(@RequestParam("renda") Long renda) {
        var lista = service.buscarCartao(renda);
        return ResponseEntity.ok(lista);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesPorClienteResponse>> getCartoesByCliente(@RequestParam String cpf) {
        var lista = clienteCartaoService.getByCpf(cpf);
      List<CartoesPorClienteResponse> resultado = lista.stream()
              .map(CartoesPorClienteResponse::fromModel).toList();
        return ResponseEntity.ok(resultado);
    }

}

