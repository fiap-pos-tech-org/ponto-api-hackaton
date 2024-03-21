package br.com.fiap.hackaton.timekeepingapi.service;

import br.com.fiap.hackaton.timekeepingapi.dto.TimeKeepingDTO;
import br.com.fiap.hackaton.timekeepingapi.message.producers.TopicoRegistroProducer;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimeKeepingService {

    private final TopicoRegistroProducer topicoRegistroProducer;

    public TimeKeepingService(TopicoRegistroProducer topicoRegistroProducer) {
        this.topicoRegistroProducer = topicoRegistroProducer;
    }

    public TimeKeepingDTO publish(TimeKeepingDTO timeKeepingDTO) {
        timeKeepingDTO.setTime(LocalDateTime.now());
        var messageId = topicoRegistroProducer.publish(timeKeepingDTO);
        return timeKeepingDTO.comId(messageId);
    }

}
