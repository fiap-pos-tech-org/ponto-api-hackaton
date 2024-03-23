package br.com.fiap.hackaton.clockregistryapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ClockRegistryDTO {

    private String id;

    @Schema(example = "123456")
    @NotNull(message = "O campo timeClockId é obrigatório")
    private Long timeClockId;

    @Schema(example = "789")
    @NotNull(message = "O campo userId é obrigatório")
    private Long userId;

    private LocalDateTime time;

    public ClockRegistryDTO() {
    }

    public ClockRegistryDTO(String id, Long timeClockId, Long userId, LocalDateTime time) {
        this.id = id;
        this.timeClockId = timeClockId;
        this.userId = userId;
        this.time = time;
    }

    public ClockRegistryDTO comId(String id) {
        return new ClockRegistryDTO(id, timeClockId, userId, time);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTimeClockId() {
        return timeClockId;
    }

    public void setTimeClockId(Long timeClockId) {
        this.timeClockId = timeClockId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
