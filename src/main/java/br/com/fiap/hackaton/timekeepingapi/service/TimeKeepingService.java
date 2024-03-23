package br.com.fiap.hackaton.timekeepingapi.service;

import br.com.fiap.hackaton.timekeepingapi.domain.TimeKeeping;
import br.com.fiap.hackaton.timekeepingapi.dto.TimeKeepingDTO;
import br.com.fiap.hackaton.timekeepingapi.dto.TimeKeepingDailyDTO;
import br.com.fiap.hackaton.timekeepingapi.exception.EntityNotFoundException;
import br.com.fiap.hackaton.timekeepingapi.message.producers.TopicoRegistroProducer;
import br.com.fiap.hackaton.timekeepingapi.repository.TimeKeepingRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
public class TimeKeepingService {

    private final TopicoRegistroProducer topicoRegistroProducer;
    private final TimeKeepingRepository timeKeepingRepository;

    public TimeKeepingService(TopicoRegistroProducer topicoRegistroProducer, TimeKeepingRepository timeKeepingRepository) {
        this.topicoRegistroProducer = topicoRegistroProducer;
        this.timeKeepingRepository = timeKeepingRepository;
    }

    public TimeKeepingDTO publish(TimeKeepingDTO timeKeepingDTO) {
        timeKeepingDTO.setTime(LocalDateTime.now());
        var messageId = topicoRegistroProducer.publish(timeKeepingDTO);
        return timeKeepingDTO.comId(messageId);
    }

    public TimeKeepingDailyDTO getClockRegistriesOfDayByUser(Long userId) {
        var currentDate = LocalDate.now();
        List<TimeKeeping> timeKeepings = timeKeepingRepository.findByUserIdAndTimeLikeOrderByTime(userId, currentDate.toString());
        if (Objects.isNull(timeKeepings) || timeKeepings.isEmpty()) {
            throw new EntityNotFoundException(String.format("Não há registro de ponto do usuário %s", userId));
        }

        var username = timeKeepings.get(0).getUser().getName();
        List<String> clockRegistries = getClockRegistries(timeKeepings);
        String totalHoursWorked = getTotalHoursWorked(timeKeepings);

        return new TimeKeepingDailyDTO(username, clockRegistries, totalHoursWorked);
    }

    private List<String> getClockRegistries(List<TimeKeeping> timeKeepings) {
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return timeKeepings.stream()
                .map(TimeKeeping::getTime)
                .map(formatter::format)
                .toList();
    }

    private String getTotalHoursWorked(List<TimeKeeping> timeKeepings) {
        timeKeepings = removeLastRegistryIfIncompleteTimeKeeping(timeKeepings);
        Duration totalHoursWorked = calculateTotalHoursWorked(timeKeepings);
        return formatTotalHoursWorked(totalHoursWorked);
    }

    private List<TimeKeeping> removeLastRegistryIfIncompleteTimeKeeping(List<TimeKeeping> timeKeepings) {
        if (timeKeepings.size() % 2 == 1) {
            return timeKeepings.stream()
                    .limit(timeKeepings.size() - 1L)
                    .toList();
        }
        return timeKeepings;
    }

    private Duration calculateTotalHoursWorked(List<TimeKeeping> timeKeepings) {
        var totalHoursWorked = Duration.ZERO;
        for (int i = 0; i < timeKeepings.size() - 1; i++) {
            var initialDate = timeKeepings.get(i).getTime();
            var endDate = timeKeepings.get(i + 1).getTime();
            var difference = Duration.between(initialDate, endDate);
            totalHoursWorked = totalHoursWorked.plus(difference);
        }
        return totalHoursWorked;
    }

    private String formatTotalHoursWorked(Duration totalHoursWorked) {
        var formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        var localTime = LocalTime.MIDNIGHT.plus(totalHoursWorked);
        return localTime.format(formatter);
    }

}
