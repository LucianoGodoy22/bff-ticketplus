package com.ticketplus.ms_bff_publico.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/api/bff/eventos")
@CrossOrigin(origins = "*") // Permitimos CORS localmente por ahora
public class BffEventoController {

    private final RestClient restClient;

    // Inyectamos la URL del MS de Eventos desde el application.properties
    public BffEventoController(@Value("${ms.eventos.url}") String msEventosUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(msEventosUrl)
                .build();
    }

    @GetMapping
    public ResponseEntity<Object> obtenerEventos() {
        // Hacemos la llamada al MS de Eventos interno
        Object eventos = restClient.get()
                .uri("/api/v1/eventos")
                .retrieve()
                .body(Object.class);
        
        return ResponseEntity.ok(eventos);
    }
}