package br.com.fiap.hackaton.clockregistryapi.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public abstract class ClockRegistryBaseDTO {

    @Hidden
    private String id;

    @Schema(example = "789")
    @NotNull(message = "O campo userId é obrigatório")
    private Long userId;

    public ClockRegistryBaseDTO() {
    }

    public ClockRegistryBaseDTO(String id, Long userId) {
        this.id = id;
        this.userId = userId;
    }

    public ClockRegistryBaseDTO(Long userId) {
        this.userId = userId;
    }

    public abstract ClockRegistryBaseDTO comId(String id);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
