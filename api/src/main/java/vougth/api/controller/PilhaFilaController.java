package vougth.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vougth.api.domain.Event;
import vougth.api.service.PilhaFilaService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/files")
@CrossOrigin
public class PilhaFilaController {
    @Autowired private PilhaFilaService service;

    @GetMapping("qtty/{qtty}")
    @Operation(summary = "Busca os últimos eventos inseridos no banco pela quantidade de registros solicitada")
    public ResponseEntity<List<Event>> findByQtty(@PathVariable Integer qtty) {
        return service.findByQtty(qtty);
    }

    @GetMapping("/import-events")
    @Operation(summary = "Importa os eventos para nossa base de dados")
    public ResponseEntity<Void> gravaArquivoTxt() {
        return service.recordTxtFile();
    }

    @GetMapping("/export-events")
    @Operation(summary = "Download do CSV dos Eventos e dos Usuários")
    public ResponseEntity<Void> getEventsTxt(HttpServletResponse response) throws IOException {
        return service.getEventsTxt(response);
    }

    @GetMapping("fila/{qttd}")
    @Operation(summary = "Busca os últimos eventos postados")
    public ResponseEntity<List<Event>> findByQttyFila(@PathVariable Integer qttd) {
        return service.findByQttyFila(qttd);
    }
}
