package io.github.paulosdoliveira.msavaliadorcartao.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import feign.FeignException;
import io.github.paulosdoliveira.msavaliadorcartao.domain.model.*;
import io.github.paulosdoliveira.msavaliadorcartao.ex.DadosClienteNotFoundException;
import io.github.paulosdoliveira.msavaliadorcartao.ex.ErroComunicacaoMicroServiceException;
import io.github.paulosdoliveira.msavaliadorcartao.ex.ErroSolicitacaoCartaoException;
import io.github.paulosdoliveira.msavaliadorcartao.infra.clients.CartoesResourceClient;
import io.github.paulosdoliveira.msavaliadorcartao.infra.clients.ClienteResourceClient;
import io.github.paulosdoliveira.msavaliadorcartao.infra.mqueue.SolicitacaoEmissaoCartaoPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class AvaliadorCreditoService {

    @Autowired
    private ClienteResourceClient clienteResourceClient;

    @Autowired
    private CartoesResourceClient cartoesResourceClient;

    @Autowired
    private SolicitacaoEmissaoCartaoPublisher solicitacaoCartaoPublisher;

    public SituacaoCliente obterSituacaoCliente(String cpf) throws Exception {
        try {
            var cartoes = cartoesResourceClient.getCartoesByCliente(cpf);
            ResponseEntity<DadosCliente> dados = clienteResourceClient.buscarCliente(cpf);
            SituacaoCliente situacao = new SituacaoCliente(dados.getBody(), cartoes.getBody());
            return situacao;
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            throw HttpStatus.NOT_FOUND.value() == status ? new DadosClienteNotFoundException() :
                    new ErroComunicacaoMicroServiceException(e.getMessage(), status);
        }
    }

    public RetornoAvaliacaoClente realizarAvalacao(String cpf, Long renda) throws Exception {
        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clienteResourceClient.buscarCliente(cpf);
            ResponseEntity<List<Cartao>> cartoesResponse = cartoesResourceClient.getCartoesRendaAte(renda);
            List<Cartao> cartoes = cartoesResponse.getBody();

            var listaCartoesAprovados = cartoes.stream().map(cartao -> {
                DadosCliente dadosCliente = dadosClienteResponse.getBody();
                BigDecimal limiteBasico = cartao.getLimiteCartao();
                BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getIdade());
                var fator = idadeBD.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado = fator.multiply(limiteBasico);

                CartaoAprovado aprovado = new CartaoAprovado();
                aprovado.setBandera(cartao.getBandeira());
                aprovado.setCartao(cartao.getNome());
                aprovado.setLimiteAprovado(limiteAprovado);
                return aprovado;
            }).toList();

            return new RetornoAvaliacaoClente(listaCartoesAprovados);
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            throw HttpStatus.NOT_FOUND.value() == status ? new DadosClienteNotFoundException() :
                    new ErroComunicacaoMicroServiceException(e.getMessage(), status);
        }

    }

    public ProtocoloSolicitacaoCartao solicitarCartao(DadosSolicitacaoEmissaoCartao dados) {

        try {
            solicitacaoCartaoPublisher.solicitarCartao(dados);
            var protocolo = UUID.randomUUID().toString();
            return new ProtocoloSolicitacaoCartao(protocolo);
        } catch (JsonProcessingException e) {
            throw new ErroSolicitacaoCartaoException(e.getMessage());
        }


    }
}
