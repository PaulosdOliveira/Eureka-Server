package io.github.paulosdoliiveira.mscartoes.infra.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.paulosdoliiveira.mscartoes.infra.repository.CartaoRepository;
import io.github.paulosdoliiveira.mscartoes.infra.repository.ClienteCartaoRepository;
import io.github.paulosdoliiveira.mscartoes.model.ClienteCartao;
import io.github.paulosdoliiveira.mscartoes.model.DadosSolicitacaoEmissaoCartao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmissaoCartaoSubscriber {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ClienteCartaoRepository clienteCartaoRepository;

    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void ReceberSolicitacaoEmissao(@Payload String payLoad) {
        var mapper = new ObjectMapper();
        try {
            var dados = mapper.readValue(payLoad, DadosSolicitacaoEmissaoCartao.class);
            var cartao = cartaoRepository.findById(dados.getIdCartao()).orElseThrow();
            ClienteCartao clienteCartao = new ClienteCartao();
            clienteCartao.setCartao(cartao);
            clienteCartao.setCpf(dados.getCpf());
            clienteCartao.setLimite(dados.getLimiteLiberado());
            clienteCartaoRepository.save(clienteCartao);

        } catch (Exception e) {
            log.error("Erro ao receber solicitação de emissão de cartao: {}", e.getMessage());
        }

    }

}
