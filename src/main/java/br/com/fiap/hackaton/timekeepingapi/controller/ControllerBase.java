package br.com.fiap.hackaton.timekeepingapi.controller;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

abstract class ControllerBase {
    URI getExpandedCurrentUri(String path, Long id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(path)
                .buildAndExpand(id)
                .toUri();
    }
}
