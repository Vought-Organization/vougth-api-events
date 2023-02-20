package vougth.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vougth.api.domain.Event;
import vougth.api.domain.User;
import vougth.api.service.EventService;
import vougth.api.service.UserService;
import vougth.api.util.FilaObjUtil;
import vougth.api.util.ListObjUtil;
import vougth.api.util.PilhaObjUtil;
import vougth.api.util.TxtAdapter;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/events")
@CrossOrigin
public class EventController {

    private final EventService eventService;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria Evento")
    public Event save(@Valid @RequestBody Event newEvent) {
        return eventService.save(newEvent);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca todos os eventos")
    public List<Event> getAllEvent() {
        return eventService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Busca evento pelo id")
    public Optional<Event> getEventById(@PathVariable int id) {
        return eventService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Deleta evento pelo id")
    public void deleteEventById(@PathVariable int id) {
        eventService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Atualiza evento pelo id e passando novo objeto Evento")
    public Event updateUserById(@PathVariable int id, @RequestBody Event updatedEvent) {
        return eventService.update(id, updatedEvent);
    }

    // Endpoint que busca os ultimos eventos inseridos no banco pela quantidade solicitada
    @GetMapping("qttd/{qttd}")
    public ResponseEntity findByQttd(@PathVariable Integer qttd) {
        List<Event> eventos = eventService.findAll();
        List<Event> ultimosEventos = new ArrayList<>();

        if (!eventos.isEmpty()) {
            if (eventos.size() <= qttd) {
                return ResponseEntity.status(200).body(eventos);
            }

            PilhaObjUtil<Event> ultimosRegistrosPilha = new PilhaObjUtil<>(eventos.size());

            for (Integer i = eventos.size() - 1; i > eventos.size() - qttd - 1; i--) {
                ultimosRegistrosPilha.push(eventos.get(i));
            }

            for (Integer i = 0; i < qttd; i++) {
                ultimosEventos.add(ultimosRegistrosPilha.pop());
            }

            return ResponseEntity.status(200).body(ultimosEventos);
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/import-eventos")
    @Operation(summary = "Importa os eventos para nossa base de dados")
    public ResponseEntity gravaArquivoTxt() {

        List<Event> events = eventService.findAll();
        List<User> users = userService.findAll();

        if (!events.isEmpty()) {

            int contaRegDados = 0;      // contador de registros de dados (para poder gravar no trailer)

            // Monta o registro de header
            String header = "eventos e usuarios ";
            Date dataDeHoje = new Date();       // Data e hora do momento, no formato padrão do Java
            SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");     // configura o padrão de formatação da data e horário
            header += formataData.format(dataDeHoje);   // Formata a data e hora para o padrão desejado
            header += "01";

            // Grava o header
            TxtAdapter.gravaRegistro(header, "eventos-usuarios.txt");


            for (Event t : events) {
                String corpo = "02 ";
                corpo += String.format("%03d ", t.getIdEvent());
                corpo += String.format("%-40.40s", t.getNameEvent());
                corpo += String.format("%-10.10s", t.getCep());
                corpo += String.format("%-10.10s", t.getCategoryEvent());
                corpo += String.format("%-50.50s", t.getAddressEvent());
                corpo += String.format("\n");
                corpo += String.format("%-25.25s", t.getDescription());
                corpo += String.format("%-30.30s", t.getCity());
                corpo += String.format("%-20.20s", t.getState());
                corpo += String.format("%-20.20s", t.getStartData());
                corpo += String.format("%-20.20s", t.getEndData());

                // Incrementa o contador de registro de dados
                contaRegDados++;

                // Grava o registro de corpo no arquivo
                TxtAdapter.gravaRegistro(corpo, "eventos-usuarios.txt");
            }

            for (User u : users) {
                String corpo = "02 ";
                corpo += String.format("%03d ", u.getIdUser());
                corpo += String.format("%-40.40s", u.getUserName());
                corpo += String.format("%-40.40s", u.getEmail());
                corpo += String.format("%-10.10s", u.getNickname());
                corpo += String.format("%-20.20s", u.getCpf());
                corpo += String.format("\n");
                corpo += String.format("%-15.15s", u.getTelefone());
                corpo += String.format("%-20.20s", u.getCep());
                corpo += String.format("%-10.10s", u.isOrganize());

                // Incrementa o contador de registro de dados
                contaRegDados++;

                // Grava o registro de corpo no arquivo
                TxtAdapter.gravaRegistro(corpo, "eventos-usuarios.txt");
            }

            // Monta e grava o registro de trailer
            String trailer = "01 ";
            trailer += String.format("%010d", contaRegDados);   // contador de registros de dados
            TxtAdapter.gravaRegistro(trailer, "eventos-usuarios.txt");

            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(204).build();
    }

    // Endpoint para fazer o export do TXT de eventos
    @GetMapping("/export-eventos")
    @Operation(summary = "Download do CSV dos Eventos e dos Usuários")
    public void getEventsTxt(HttpServletResponse response) throws IOException {

        List<Event> listaJava = eventService.findAll();
        List<User> listaJavaUser = userService.findAll();

        ListObjUtil<Event> lista = new ListObjUtil<>(listaJava.size());
        ListObjUtil<User> listaUser = new ListObjUtil<>(listaJavaUser.size());

        for (int i = 0; i < eventService.count(); i++) {
            lista.adicionar(listaJava.get(i));
        }

        for (int i = 0; i < userService.count(); i++) {
            listaUser.adicionar(listaJavaUser.get(i));
        }

        TxtAdapter.downloadTxtEvent(response.getWriter(), lista, listaUser);
        response.setHeader("Content-type: application/force-download, Content-Disposition", "attachment; filename=" + "eventos-usuarios.txt");
        response.setStatus(200);
    }

    @GetMapping("fila/{qttd}")
    @Operation(summary = "Busca os últimos eventos postados")
    public ResponseEntity findByQttdFila(@PathVariable Integer qttd) {
        List<Event> eventos = eventService.findAll();
        List<Event> ultimosEventos = new ArrayList<>();

        if (!eventos.isEmpty()) {
            if (eventos.size() <= qttd) {
                return ResponseEntity.status(200).body(eventos);
            }

            FilaObjUtil<Event> ultimosRegistrosFila = new FilaObjUtil<>(eventos.size());

            for (Integer i = eventos.size() - 1; i > eventos.size() - qttd - 1; i--) {
                ultimosRegistrosFila.insert(eventos.get(i));
            }

            for (Integer i = 0; i < qttd; i++) {
                ultimosEventos.add(ultimosRegistrosFila.poll());
            }

            return ResponseEntity.status(200).body(ultimosEventos);
        }
        return ResponseEntity.status(204).build();
    }
}

