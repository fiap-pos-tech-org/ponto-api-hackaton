package br.com.fiap.hackaton.clockregistryapi.dto;

public class ClockRegistryReportDTO extends ClockRegistryBaseDTO {

    private Integer month;

    public ClockRegistryReportDTO() {
    }

    public ClockRegistryReportDTO(String id, Long userId, Integer month) {
        super(id, userId);
        this.month = month;
    }

    public ClockRegistryReportDTO(Long userId, Integer month) {
        super(userId);
        this.month = month;
    }

    @Override
    public ClockRegistryReportDTO comId(String id) {
        return new ClockRegistryReportDTO(id, getUserId(), month);
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}
