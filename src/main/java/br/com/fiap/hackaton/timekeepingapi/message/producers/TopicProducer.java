package br.com.fiap.hackaton.timekeepingapi.message.producers;

import br.com.fiap.hackaton.timekeepingapi.dto.TimeKeepingDTO;

public interface TopicProducer {
    String publish(TimeKeepingDTO timeKeepingDTO);
}
