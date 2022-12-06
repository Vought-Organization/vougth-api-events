package vougth.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vougth.api.domain.Event;
import vougth.api.domain.EventUser;
import vougth.api.domain.User;
import vougth.api.repository.EventRepository;
import vougth.api.repository.EventUserRepository;
import vougth.api.repository.UserRepository;
import vougth.api.uteis.FilaObj;
import vougth.api.uteis.ListObj;
import vougth.api.uteis.PilhaObj;
import vougth.api.uteis.TxtAdapter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("v1/events")
@CrossOrigin
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event newEvent) {
//        newEvent.getUser().setOrganize(true);
        eventRepository.save(newEvent);
        return ResponseEntity.status(201).body(newEvent);
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvent() {
        List<Event> eventList = eventRepository.findAll();
        return eventList.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(eventList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable int id) {
        return ResponseEntity.of(eventRepository.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEventById(@PathVariable int id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateUserById(@PathVariable int id, @RequestBody Event updatedEvent) {
        if (eventRepository.existsById(id)) {
            updatedEvent.setIdEvent(id);
            eventRepository.save(updatedEvent);
            return ResponseEntity.status(200).body(updatedEvent);
        }
        return ResponseEntity.status(404).build();
    }

    // Endpoint que busca os ultimos eventos inseridos no banco pela quantidade solicitada
    @GetMapping("qttd/{qttd}")
    public ResponseEntity findByQttd(@PathVariable Integer qttd) {
        List<Event> eventos = eventRepository.findAll();
        List<Event> ultimosEventos = new ArrayList<>();

        if (!eventos.isEmpty()) {
            if (eventos.size() <= qttd) {
                return ResponseEntity.status(200).body(eventos);
            }

            PilhaObj<Event> ultimosRegistrosPilha = new PilhaObj<>(eventos.size());

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
    public ResponseEntity gravaArquivoTxt() {

        List<Event> events = eventRepository.findAll();
        List<User>  users = userRepository.findAll();

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
    public void getEventsTxt(HttpServletResponse response) throws IOException {

        List<Event> listaJava = eventRepository.findAll();
        List<User> listaJavaUser = userRepository.findAll();

        ListObj<Event> lista = new ListObj<>(listaJava.size());
        ListObj<User> listaUser = new ListObj<>(listaJavaUser.size());

        for (int i = 0; i < eventRepository.count(); i++) {
            lista.adicionar(listaJava.get(i));
        }

        for (int i = 0; i < userRepository.count(); i++) {
            listaUser.adicionar(listaJavaUser.get(i));
        }

        TxtAdapter.downloadTxtEvent(response.getWriter(), lista, listaUser);
        response.setHeader("Content-type: application/force-download, Content-Disposition", "attachment; filename=" + "eventos-usuarios.txt");
        response.setStatus(200);
    }
}

