package br.com.fiap.hackaton.clockregistryapi.service;

import br.com.fiap.hackaton.clockregistryapi.domain.ClockRegistry;
import br.com.fiap.hackaton.clockregistryapi.dto.ClockRegistryBaseDTO;
import br.com.fiap.hackaton.clockregistryapi.dto.ClockRegistryDTO;
import br.com.fiap.hackaton.clockregistryapi.dto.ClockRegistryDailyDTO;
import br.com.fiap.hackaton.clockregistryapi.dto.ClockRegistryReportDTO;
import br.com.fiap.hackaton.clockregistryapi.exception.EntityNotFoundException;
import br.com.fiap.hackaton.clockregistryapi.message.producers.TopicoRegistroProducer;
import br.com.fiap.hackaton.clockregistryapi.repository.ClockRegistryRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
public class ClockRegistryService {

    private final TopicoRegistroProducer topicoRegistroProducer;
    private final ClockRegistryRepository clockRegistryRepository;
    private final UserService userService;

    public ClockRegistryService(TopicoRegistroProducer topicoRegistroProducer,
                                ClockRegistryRepository clockRegistryRepository,
                                UserService userService) {
        this.topicoRegistroProducer = topicoRegistroProducer;
        this.clockRegistryRepository = clockRegistryRepository;
        this.userService = userService;
    }

    public ClockRegistryDTO publishRegistryToTopicoRegistro(ClockRegistryBaseDTO clockRegistryBaseDTO) {
        userService.findById(clockRegistryBaseDTO.getUserId());
        var clockRegistryDTO = (ClockRegistryDTO) clockRegistryBaseDTO;
        clockRegistryDTO.setTime(LocalDateTime.now());
        var messageId = topicoRegistroProducer.publish(clockRegistryDTO);
        return clockRegistryDTO.withId(messageId);
    }

    public ClockRegistryDailyDTO getClockRegistriesOfDayByUser(Long userId) {
        var currentDate = LocalDate.now();
        List<ClockRegistry> clockRegistries = clockRegistryRepository.findByUserIdAndTimeLikeOrderByTime(userId, currentDate.toString());
        if (Objects.isNull(clockRegistries) || clockRegistries.isEmpty()) {
            var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            var currentDateFormated = formatter.format(currentDate);
            throw new EntityNotFoundException(String.format("Não há registro de ponto do usuário %s do dia %s", userId, currentDateFormated));
        }

        var username = clockRegistries.get(0).getUser().getName();
        List<String> clockRegistriesAsString = getClockRegistries(clockRegistries);
        String totalHoursWorked = getTotalHoursWorked(clockRegistries);

        return new ClockRegistryDailyDTO(username, clockRegistriesAsString, totalHoursWorked);
    }

    public ClockRegistryReportDTO publishReportToTopicoRegistro(ClockRegistryBaseDTO clockRegistryBaseDTO) {
        var clockRegistryDTO = (ClockRegistryReportDTO) clockRegistryBaseDTO;
        var currentDate = LocalDate.now().withMonth(clockRegistryDTO.getMonth());
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        var timePattern = formatter.format(currentDate);
        List<ClockRegistry> clockRegistries = clockRegistryRepository.findByUserIdAndTimeLikeOrderByTime(clockRegistryBaseDTO.getUserId(), timePattern);
        if (Objects.isNull(clockRegistries) || clockRegistries.isEmpty()) {
            throw new EntityNotFoundException(String.format("Não há registro de ponto do usuário %s do mês %s", clockRegistryBaseDTO.getUserId(), timePattern));
        }

        var messageId = topicoRegistroProducer.publish(clockRegistryDTO);
        return clockRegistryDTO.withId(messageId);
    }

    private List<String> getClockRegistries(List<ClockRegistry> clockRegistries) {
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return clockRegistries.stream()
                .map(ClockRegistry::getTime)
                .map(formatter::format)
                .toList();
    }

    private String getTotalHoursWorked(List<ClockRegistry> clockRegistries) {
        clockRegistries = removeLastRegistryIfIncompleteClockRegistry(clockRegistries);
        Duration totalHoursWorked = calculateTotalHoursWorked(clockRegistries);
        return formatTotalHoursWorked(totalHoursWorked);
    }

    private List<ClockRegistry> removeLastRegistryIfIncompleteClockRegistry(List<ClockRegistry> clockRegistries) {
        if (clockRegistries.size() % 2 == 1) {
            return clockRegistries.stream()
                    .limit(clockRegistries.size() - 1L)
                    .toList();
        }
        return clockRegistries;
    }

    private Duration calculateTotalHoursWorked(List<ClockRegistry> clockRegistries) {
        var totalHoursWorked = Duration.ZERO;
        for (int i = 0; i < clockRegistries.size() - 1; i++) {
            var initialDate = clockRegistries.get(i).getTime();
            var endDate = clockRegistries.get(i + 1).getTime();
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
