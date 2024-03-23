package br.com.fiap.hackaton.clockregistryapi.dto;

import br.com.fiap.hackaton.clockregistryapi.domain.Role;
import br.com.fiap.hackaton.clockregistryapi.domain.User;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class UserDTO {

    @Hidden
    private Long id;

    @Schema(example = "jhon")
    @NotBlank(message = "O campo username é obrigatório")
    private String username;

    @Schema(example = "pass")
    @NotBlank(message = "O campo password é obrigatório")
    private String password;

    @Schema(example = "jhon@email.com")
    @NotBlank(message = "O campo email é obrigatório")
    private String email;

    @Schema(example = "Jhon Doe")
    @NotBlank(message = "O campo name é obrigatório")
    private String name;

    @Hidden
    private LocalDateTime creationDate;

    @Schema(example = "ADMIN")
    @NotBlank(message = "O campo role é obrigatório")
    private Role role;

    @Hidden
    private String token;

    public UserDTO() {
    }

    public UserDTO(String username, String email, String name, Role role, String token) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.creationDate = LocalDateTime.now();
        this.role = role;
        this.token = token;
    }

    public UserDTO(Long id, String username, String email, String name, LocalDateTime creationDate, Role role, String token) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.name = name;
        this.creationDate = creationDate;
        this.role = role;
        this.token = token;
    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.name = user.getName();
        this.creationDate = user.getCreationDate();
        this.role = user.getRole();
    }

    public UserDTO withToken(String token) {
        return new UserDTO(id, username, email, name, creationDate, role, token);
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
