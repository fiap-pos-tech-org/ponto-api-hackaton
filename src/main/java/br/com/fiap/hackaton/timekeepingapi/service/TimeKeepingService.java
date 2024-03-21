package br.com.fiap.hackaton.timekeepingapi.service;

import br.com.fiap.hackaton.timekeepingapi.domain.TimeKeeping;
import br.com.fiap.hackaton.timekeepingapi.repository.TimeKeepingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimeKeepingService {

    private final TimeKeepingRepository timeKeepingRepository;
    private final UserService userService;

    public TimeKeepingService(TimeKeepingRepository timeKeepingRepository, UserService userService) {
        this.timeKeepingRepository = timeKeepingRepository;
        this.userService = userService;
    }

    public TimeKeeping save(TimeKeeping timeKeeping) {
        var user = userService.findById(timeKeeping.getUser().getId());
        timeKeeping.setUser(user);
        timeKeeping.setTime(LocalDateTime.now());
        return timeKeepingRepository.save(timeKeeping);
    }

}
