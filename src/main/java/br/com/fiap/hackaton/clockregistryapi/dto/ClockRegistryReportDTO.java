package br.com.fiap.hackaton.clockregistryapi.dto;

public class ClockRegistryReportDTO extends ClockRegistryBaseDTO {

    private String yearMonth;

    public ClockRegistryReportDTO() {
    }

    public ClockRegistryReportDTO(String id, Long userId, String yearMonth) {
        super(id, userId);
        this.yearMonth = yearMonth;
    }

    public ClockRegistryReportDTO(Long userId, String yearMonth) {
        super(userId);
        this.yearMonth = yearMonth;
    }

    @Override
    public ClockRegistryReportDTO withId(String id) {
        return new ClockRegistryReportDTO(id, getUserId(), yearMonth);
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }
}
