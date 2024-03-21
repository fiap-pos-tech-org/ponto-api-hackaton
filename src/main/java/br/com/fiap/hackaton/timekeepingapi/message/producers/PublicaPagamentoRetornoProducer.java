//package br.com.fiap.hackaton.timekeepingapi.message.producers;
//
//import br.com.fiap.hackaton.timekeepingapi.core.dtos.MensagemDTOBase;
//import br.com.fiap.hackaton.timekeepingapi.core.ports.out.cobranca.PublicaPagamentoRetornoOutputPort;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.stereotype.Component;
//import software.amazon.awssdk.services.sns.SnsClient;
//import software.amazon.awssdk.services.sns.model.PublishRequest;
//
//@Component
//public class PublicaPagamentoRetornoProducer implements PublicaPagamentoRetornoOutputPort {
//
//    private final Logger logger = LogManager.getLogger(PublicaPagamentoRetornoProducer.class);
//
//    private final SnsClient snsClient;
//    private final ObjectMapper mapper;
//
//    public PublicaPagamentoRetornoProducer(SnsClient snsClient, ObjectMapper mapper) {
//        this.snsClient = snsClient;
//        this.mapper = mapper;
//    }
//
//    @Override
//    public void publicar(MensagemDTOBase mensagem, String topicoArn) {
//        String message;
//        try {
//            message = mapper.writeValueAsString(mensagem);
//        } catch (JsonProcessingException e) {
//            logger.error("erro ao serializar mensagem: {}", e.getMessage());
//            throw new RuntimeException(e);
//        }
//
//        PublishRequest request = PublishRequest.builder()
//                .message(message)
//                .topicArn(topicoArn)
//                .build();
//
//        var response = snsClient.publish(request);
//        logger.info("mensagem com id {} publicada com sucesso", response.messageId());
//    }
//}
