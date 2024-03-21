package br.com.fiap.hackaton.timekeepingapi.controller;

import br.com.fiap.hackaton.timekeepingapi.dto.TimeKeepingDTO;
import br.com.fiap.hackaton.timekeepingapi.service.TimeKeepingService;
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

@Tag(name = "Ponto", description = "APIs para registro de ponto ")
@Validated
@RestController
@RequestMapping("/timekeepings")
public class TimeKeepingController extends ControllerBase {

    private final Logger logger = LogManager.getLogger(TimeKeepingController.class);

    private final TimeKeepingService timeKeepingService;

    public TimeKeepingController(TimeKeepingService timeKeepingService) {
        this.timeKeepingService = timeKeepingService;
    }

    @Operation(summary = "Registra um novo ponto")
    @PostMapping(value = "/registry")
    public ResponseEntity<TimeKeepingDTO> registrar(@Valid @RequestBody TimeKeepingDTO timeKeepingDTO) {
        var timeKeeping = timeKeepingService.save(TimeKeepingDTO.toTimeKeeping(timeKeepingDTO));
        var uri = getExpandedCurrentUri("/{id}", timeKeeping.getId());
        logger.info("registro {} efetuado com sucesso", timeKeeping.getTime());
        return ResponseEntity.created(uri).body(new TimeKeepingDTO(timeKeeping));
    }
}
