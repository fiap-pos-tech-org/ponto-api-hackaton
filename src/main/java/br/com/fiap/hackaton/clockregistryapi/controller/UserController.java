package br.com.fiap.hackaton.clockregistryapi.controller;

import br.com.fiap.hackaton.clockregistryapi.dto.UserDTO;
import br.com.fiap.hackaton.clockregistryapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Usuário", description = "APIs para controle de usuário")
@Validated
@RestController
@RequestMapping("/user")
public class UserController extends ControllerBase {

    private final Logger logger = LogManager.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Cadastra um novo usuário")
    @PostMapping
    public ResponseEntity<UserDTO> save(@Valid @RequestBody UserDTO userDTO) {
        UserDTO userSaved = userService.save(userDTO);
        var uri = getExpandedCurrentUri("/{id}", String.valueOf(userDTO.getId()));
        logger.info("usuario {} registrado com sucesso", userSaved.getId());
        return ResponseEntity.created(uri).body(userSaved);
    }

}
