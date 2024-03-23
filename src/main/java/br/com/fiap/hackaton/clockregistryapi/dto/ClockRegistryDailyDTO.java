package br.com.fiap.hackaton.clockregistryapi.dto;

import java.util.List;

public class ClockRegistryDailyDTO {

    private String username;
    private List<String> clockRegistries;
    private String totalHoursWorked;

    public ClockRegistryDailyDTO() {
    }

    public ClockRegistryDailyDTO(String username, List<String> clockRegistries, String totalHoursWorked) {
        this.username = username;
        this.clockRegistries = clockRegistries;
        this.totalHoursWorked = totalHoursWorked;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getClockRegistries() {
        return clockRegistries;
    }

    public void setClockRegistries(List<String> clockRegistries) {
        this.clockRegistries = clockRegistries;
    }

    public String getTotalHoursWorked() {
        return totalHoursWorked;
    }

    public void setTotalHoursWorked(String totalHoursWorked) {
        this.totalHoursWorked = totalHoursWorked;
    }
}
