package br.com.fiap.hackaton.clockregistryapi.message.producers;

import br.com.fiap.hackaton.clockregistryapi.dto.ClockRegistryBaseDTO;

public interface TopicProducer {
    String publish(ClockRegistryBaseDTO clockRegistryBaseDTO);
}
