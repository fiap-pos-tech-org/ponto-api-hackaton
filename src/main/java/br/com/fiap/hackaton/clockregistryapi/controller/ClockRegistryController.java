package br.com.fiap.hackaton.clockregistryapi.controller;

import br.com.fiap.hackaton.clockregistryapi.dto.ClockRegistryDTO;
import br.com.fiap.hackaton.clockregistryapi.dto.ClockRegistryDailyDTO;
import br.com.fiap.hackaton.clockregistryapi.dto.ClockRegistryReportDTO;
import br.com.fiap.hackaton.clockregistryapi.service.ClockRegistryService;
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

@Tag(name = "Registro de Ponto", description = "APIs para registro de ponto")
@Validated
@RestController
@RequestMapping("/clock-registries")
public class ClockRegistryController extends ControllerBase {

    private final Logger logger = LogManager.getLogger(ClockRegistryController.class);

    private final ClockRegistryService clockRegistryService;

    public ClockRegistryController(ClockRegistryService clockRegistryService) {
        this.clockRegistryService = clockRegistryService;
    }

    @Operation(summary = "Registra um novo ponto")
    @PostMapping(value = "/registry")
    public ResponseEntity<ClockRegistryDTO> registry(@Valid @RequestBody ClockRegistryDTO clockRegistryDTO) {
        ClockRegistryDTO publishedMessage = clockRegistryService.publishRegistryToTopicoRegistro(clockRegistryDTO);
        var uri = getExpandedCurrentUri("/{id}", publishedMessage.getId());
        logger.info("registro {} de ponto efetuado com sucesso", publishedMessage.getId());
        return ResponseEntity.created(uri).body(publishedMessage);
    }

    @Operation(summary = "Busca registro do ponto do dia por usuário")
    @GetMapping(value = "/registry")
    ResponseEntity<ClockRegistryDailyDTO> getClockRegistriesOfDayByUser(@Parameter(example = "1")
                                                                        @RequestParam
                                                                        @Pattern(regexp = "^\\d*$", message = "O id do usuário deve conter apenas números") String userId) {
        ClockRegistryDailyDTO registryOfDayByUser = clockRegistryService.getClockRegistriesOfDayByUser(Long.parseLong(userId));
        return ResponseEntity.ok().body(registryOfDayByUser);
    }

    @Operation(summary = "Busca relatório de ponto mensal por usuário")
    @GetMapping(value = "/report")
    ResponseEntity<ClockRegistryReportDTO> getReportClockRegistryMonthlyByUser(@Parameter(example = "1")
                                                                               @RequestParam
                                                                               @Pattern(regexp = "^\\d*$", message = "O id do usuário deve conter apenas números") String userId,
                                                                               @Parameter(example = "1")
                                                                               @RequestParam
                                                                               @Pattern(regexp = "^\\d*$", message = "O mês deve conter apenas números") String month) {
        var clockRegistryReportDTO = new ClockRegistryReportDTO(Long.parseLong(userId), Integer.parseInt(month));
        ClockRegistryReportDTO registryOfDayByUser = clockRegistryService.publishReportToTopicoRegistro(clockRegistryReportDTO);
        return ResponseEntity.ok().body(registryOfDayByUser);
    }

}
