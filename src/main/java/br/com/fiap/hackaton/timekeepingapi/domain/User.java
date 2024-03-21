package br.com.fiap.hackaton.timekeepingapi.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private Long username;

    @Column(name = "password")
    private Long password;

    @Column(name = "email")
    private Long email;

    @Column(name = "name")
    private Long name;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, targetEntity = TimeKeeping.class)
    private List<TimeKeeping> timeKeepings;

    @PrePersist
    protected void onCreate() {
        creationDate = LocalDateTime.now();
    }

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(Long username, Long password, Long email, Long name, List<TimeKeeping> timeKeepings) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.timeKeepings = timeKeepings;
    }

    public Long getId() {
        return id;
    }

    public Long getUsername() {
        return username;
    }

    public void setUsername(Long username) {
        this.username = username;
    }

    public Long getPassword() {
        return password;
    }

    public void setPassword(Long password) {
        this.password = password;
    }

    public Long getEmail() {
        return email;
    }

    public void setEmail(Long email) {
        this.email = email;
    }

    public Long getName() {
        return name;
    }

    public void setName(Long name) {
        this.name = name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public List<TimeKeeping> getTimeKeepings() {
        return timeKeepings;
    }

    public void setTimeKeepings(List<TimeKeeping> timeKeepings) {
        this.timeKeepings = timeKeepings;
    }
}
