package br.com.fiap.hackaton.clockregistryapi.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, targetEntity = ClockRegistry.class)
    private List<ClockRegistry> clockRegistries;

    @PrePersist
    protected void onCreate() {
        creationDate = LocalDateTime.now();
    }

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(String username, String password, String email, String name) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
    }

    public User(String username, String password, String email, String name, List<ClockRegistry> clockRegistries) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.clockRegistries = clockRegistries;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public List<ClockRegistry> getClockRegistries() {
        return clockRegistries;
    }

    public void setClockRegistries(List<ClockRegistry> clockRegistries) {
        this.clockRegistries = clockRegistries;
    }
}
