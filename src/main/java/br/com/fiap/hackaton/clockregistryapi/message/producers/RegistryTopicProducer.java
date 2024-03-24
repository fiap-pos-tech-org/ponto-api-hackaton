package br.com.fiap.hackaton.clockregistryapi.message.producers;

import br.com.fiap.hackaton.clockregistryapi.dto.ClockRegistryBaseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Component
public class RegistryTopicProducer implements TopicProducer {

    private final Logger logger = LogManager.getLogger(RegistryTopicProducer.class);

    private final SnsClient snsClient;
    private final ObjectMapper mapper;
    private final String registryTopicArn;

    public RegistryTopicProducer(SnsClient snsClient, ObjectMapper mapper, @Value("${aws.sns.registry-topic-arn}") String registryTopicArn) {
        this.snsClient = snsClient;
        this.mapper = mapper;
        this.registryTopicArn = registryTopicArn;
    }

    @Override
    public String publish(ClockRegistryBaseDTO clockRegistryBaseDTO) {
        String message;
        try {
            message = mapper.writeValueAsString(clockRegistryBaseDTO);
        } catch (JsonProcessingException e) {
            logger.error("erro ao serializar mensagem: {}", e.getMessage());
            throw new RuntimeException(e);
        }

        var request = PublishRequest.builder()
                .message(message)
                .topicArn(registryTopicArn)
                .build();

        var response = snsClient.publish(request);
        logger.info("mensagem com id {} publicada com sucesso no tópico registry_topic", response.messageId());
        return response.messageId();
    }
}
