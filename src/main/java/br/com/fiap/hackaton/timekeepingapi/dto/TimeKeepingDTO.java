package br.com.fiap.hackaton.timekeepingapi.dto;

import br.com.fiap.hackaton.timekeepingapi.domain.TimeKeeping;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class TimeKeepingDTO {

    @Schema(example = "123456")
    @NotNull(message = "O campo timeClockId é obrigatório")
    private Long timeClockId;

    @Schema(example = "789")
    @NotNull(message = "O campo userId é obrigatório")
    private Long userId;

    private LocalDateTime time;

    public TimeKeepingDTO() {
    }

    public TimeKeepingDTO(TimeKeeping timeKeeping) {
        this.timeClockId = timeKeeping.getTimeClockId();
        this.userId = timeKeeping.getUser().getId();
        this.time = timeKeeping.getTime();
    }

    public static TimeKeeping toTimeKeeping(TimeKeepingDTO timeKeepingDTO) {
        return new TimeKeeping(timeKeepingDTO.getTimeClockId(), timeKeepingDTO.getTime(), UserDTO.toUser(timeKeepingDTO.getUserId()));
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
