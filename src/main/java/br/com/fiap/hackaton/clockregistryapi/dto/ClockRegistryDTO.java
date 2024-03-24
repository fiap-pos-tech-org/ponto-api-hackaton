package br.com.fiap.hackaton.clockregistryapi.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ClockRegistryDTO extends ClockRegistryBaseDTO {

    @Schema(example = "123456")
    @NotNull(message = "O campo timeClockId é obrigatório")
    private Long timeClockId;

    @Hidden
    private LocalDateTime time;

    public ClockRegistryDTO() {
    }

    public ClockRegistryDTO(String id, Long userId, Long timeClockId, LocalDateTime time) {
        super(id, userId);
        this.timeClockId = timeClockId;
        this.time = time;
    }

    @Override
    public ClockRegistryDTO withId(String id) {
        return new ClockRegistryDTO(id, getUserId(), timeClockId, time);
    }

    public Long getTimeClockId() {
        return timeClockId;
    }

    public void setTimeClockId(Long timeClockId) {
        this.timeClockId = timeClockId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
