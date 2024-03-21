package br.com.fiap.hackaton.timekeepingapi.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class TimeKeeping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_clock_id")
    private Long timeClockId;

    @Column(name = "time")
    private LocalDateTime time;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public TimeKeeping() {
    }

    public TimeKeeping(Long timeClockId, LocalDateTime time, User user) {
        this.timeClockId = timeClockId;
        this.time = time;
        this.user = user;
    }

    public Long getId() {
        return id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
