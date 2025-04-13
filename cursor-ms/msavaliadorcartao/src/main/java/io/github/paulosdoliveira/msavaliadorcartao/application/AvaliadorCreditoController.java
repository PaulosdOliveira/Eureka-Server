package io.github.paulosdoliveira.msavaliadorcartao.application;

import io.github.paulosdoliveira.msavaliadorcartao.domain.model.DadosAvaliacao;
import io.github.paulosdoliveira.msavaliadorcartao.domain.model.DadosSolicitacaoEmissaoCartao;
import io.github.paulosdoliveira.msavaliadorcartao.domain.model.ProtocoloSolicitacaoCartao;
import io.github.paulosdoliveira.msavaliadorcartao.domain.model.SituacaoCliente;
import io.github.paulosdoliveira.msavaliadorcartao.ex.DadosClienteNotFoundException;
import io.github.paulosdoliveira.msavaliadorcartao.ex.ErroComunicacaoMicroServiceException;
import io.github.paulosdoliveira.msavaliadorcartao.ex.ErroSolicitacaoCartaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("avaliacoes-credito")
public class AvaliadorCreditoController {

    @Autowired
    private AvaliadorCreditoService avaliadorCreditoService;

    @GetMapping
    public String status() {
        return "Estou funcionando";
    }

    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") String cpf) throws Exception {
        try {
            SituacaoCliente situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
            return ResponseEntity.ok(situacaoCliente);
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroServiceException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }

    }

    @PostMapping
    public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacao dados) {
        try {
            var retornoAvaliacao = avaliadorCreditoService.realizarAvalacao(dados.getCpf(), dados.getRenda());
            return ResponseEntity.ok(retornoAvaliacao);
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErroComunicacaoMicroServiceException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("solicitacao-cartao")
    public ResponseEntity solicitarCartao(@RequestBody DadosSolicitacaoEmissaoCartao dados) {
        try {
            ProtocoloSolicitacaoCartao protocolo = avaliadorCreditoService.solicitarCartao(dados);
            return ResponseEntity.ok(protocolo);
        } catch (ErroSolicitacaoCartaoException e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
