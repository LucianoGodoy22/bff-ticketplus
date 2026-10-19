package com.ticketplus.ms_bff_publico.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/api/bff/pagos")
@CrossOrigin(origins = "*") 
public class BffPagoController {

    private final RestClient restClient;

    public BffPagoController(@Value("${ms.pagos.url}") String msPagosUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(msPagosUrl)
                .build();
    }

    @PostMapping
    public ResponseEntity<Object> procesarPago(@RequestBody Object pagoRequest) {
        // Redirige el JSON del frontend directamente al productor de RabbitMQ
        Object response = restClient.post()
                .uri("/api/v1/pagos")
                .body(pagoRequest)
                .retrieve()
                .body(Object.class);
        
        return ResponseEntity.ok(response);
    }
}