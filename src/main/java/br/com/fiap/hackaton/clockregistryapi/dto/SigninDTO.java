package br.com.fiap.hackaton.clockregistryapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public class SigninDTO {

    @Schema(example = "jhon")
    @NotBlank(message = "O campo username é obrigatório")
    private String username;

    @Schema(example = "pass")
    @NotBlank(message = "O campo password é obrigatório")
    private String password;

    public SigninDTO() {
    }

    public SigninDTO(String username, String password) {
        this.username = username;
        this.password = password;
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

}
