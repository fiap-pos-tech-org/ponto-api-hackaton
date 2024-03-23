package br.com.fiap.hackaton.timekeepingapi.controller;

import br.com.fiap.hackaton.timekeepingapi.dto.TimeKeepingDTO;
import br.com.fiap.hackaton.timekeepingapi.dto.TimeKeepingDailyDTO;
import br.com.fiap.hackaton.timekeepingapi.service.TimeKeepingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<TimeKeepingDTO> registry(@Valid @RequestBody TimeKeepingDTO timeKeepingDTO) {
        TimeKeepingDTO publishedMessage = timeKeepingService.publish(timeKeepingDTO);
        var uri = getExpandedCurrentUri("/{id}", publishedMessage.getId());
        logger.info("registro {} de ponto efetuado com sucesso", publishedMessage.getId());
        return ResponseEntity.created(uri).body(publishedMessage);
    }

    @Operation(summary = "Busca registro do ponto do dia por usuário")
    @GetMapping(value = "/registry")
    ResponseEntity<TimeKeepingDailyDTO> getClockRegistriesOfDayByUser(@Parameter(example = "1")
                                                               @RequestParam
                                                               @Pattern(regexp = "^\\d*$", message = "O id do usuário deve conter apenas números") String userId) {
        TimeKeepingDailyDTO registryOfDayByUser = timeKeepingService.getClockRegistriesOfDayByUser(Long.parseLong(userId));
        return ResponseEntity.ok().body(registryOfDayByUser);
    }

}
