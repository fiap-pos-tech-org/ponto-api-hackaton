package br.com.fiap.hackaton.clockregistryapi.message.producers;

import br.com.fiap.hackaton.clockregistryapi.dto.ClockRegistryDTO;

public interface TopicProducer {
    String publish(ClockRegistryDTO clockRegistryDTO);
}
