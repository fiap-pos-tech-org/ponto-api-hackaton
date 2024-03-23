package br.com.fiap.hackaton.clockregistryapi.dto;

public class ClockRegistryReportDTO extends ClockRegistryBaseDTO {

    private Long month;

    public ClockRegistryReportDTO() {
    }

    public ClockRegistryReportDTO(String id, Long userId, Long month) {
        super(id, userId);
        this.month = month;
    }

    public ClockRegistryReportDTO(Long userId, Long month) {
        super(userId);
        this.month = month;
    }

    @Override
    public ClockRegistryReportDTO comId(String id) {
        return new ClockRegistryReportDTO(id, getUserId(), month);
    }

    public Long getMonth() {
        return month;
    }

    public void setMonth(Long month) {
        this.month = month;
    }
}
