package br.com.fiap.hackaton.clockregistryapi.message.producers;

import br.com.fiap.hackaton.clockregistryapi.dto.ClockRegistryDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Component
public class TopicoRegistroProducer implements TopicProducer {

    private final Logger logger = LogManager.getLogger(TopicoRegistroProducer.class);

    private final SnsClient snsClient;
    private final ObjectMapper mapper;
    private final String topicoRegistroArn;

    public TopicoRegistroProducer(SnsClient snsClient, ObjectMapper mapper, @Value("${aws.sns.topico-registro-arn}") String topicoRegistroArn) {
        this.snsClient = snsClient;
        this.mapper = mapper;
        this.topicoRegistroArn = topicoRegistroArn;
    }

    @Override
    public String publish(ClockRegistryDTO clockRegistryDTO) {
        String message;
        try {
            message = mapper.writeValueAsString(clockRegistryDTO);
        } catch (JsonProcessingException e) {
            logger.error("erro ao serializar mensagem: {}", e.getMessage());
            throw new RuntimeException(e);
        }

        var request = PublishRequest.builder()
                .message(message)
                .topicArn(topicoRegistroArn)
                .build();

        var response = snsClient.publish(request);
        logger.info("mensagem com id {} publicada com sucesso no topico-registro", response.messageId());
        return response.messageId();
    }
}
