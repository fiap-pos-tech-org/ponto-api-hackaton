package br.com.fiap.hackaton.clockregistryapi.controller;

import br.com.fiap.hackaton.clockregistryapi.dto.JwtTokenDTO;
import br.com.fiap.hackaton.clockregistryapi.dto.SigninDTO;
import br.com.fiap.hackaton.clockregistryapi.dto.UserDTO;
import br.com.fiap.hackaton.clockregistryapi.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Autenticação", description = "APIs para autenticação de usuário")
@Validated
@RestController
@RequestMapping("/auth")
public class AuthenticationController extends ControllerBase {

    private final Logger logger = LogManager.getLogger(AuthenticationController.class);

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Operation(summary = "Cadastra um novo usuário")
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@RequestBody UserDTO userDTO) {
        UserDTO userSaved = authenticationService.signup(userDTO);
        var uri = getExpandedCurrentUri("/{id}", String.valueOf(userDTO.getId()));
        logger.info("usuario {} cadastrado com sucesso", userSaved.getId());
        return ResponseEntity.created(uri).body(userSaved);
    }

    @Operation(summary = "Autentica um usuário")
    @PostMapping("/signin")
    public ResponseEntity<JwtTokenDTO> signin(@RequestBody SigninDTO signinDTO) {
        var token = authenticationService.signin(signinDTO);
        return ResponseEntity.ok(token);
    }

}
