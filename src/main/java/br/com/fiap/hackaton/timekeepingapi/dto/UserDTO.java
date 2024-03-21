package br.com.fiap.hackaton.timekeepingapi.dto;

import br.com.fiap.hackaton.timekeepingapi.domain.TimeKeeping;
import br.com.fiap.hackaton.timekeepingapi.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class UserDTO {

    @Schema(example = "123456")
    @NotNull(message = "O campo timeClockId é obrigatório")
    private Long timeClockId;

    @Schema(example = "789")
    @NotNull(message = "O campo userId é obrigatório")
    private Long userId;

    private LocalDateTime time;

    public UserDTO() {
    }

    public UserDTO(TimeKeeping timeKeeping) {
        this.timeClockId = timeKeeping.getTimeClockId();
        this.userId = timeKeeping.getUser().getId();
        this.time = timeKeeping.getTime();
    }

    public static User toUser(Long id) {
        return new User(id);
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
