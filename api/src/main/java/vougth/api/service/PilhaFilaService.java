package vougth.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import vougth.api.domain.Event;
import vougth.api.domain.User;
import vougth.api.exception.EventNotExistsException;
import vougth.api.exception.EventNotFoundException;
import vougth.api.util.FilaObjUtil;
import vougth.api.util.ListObjUtil;
import vougth.api.util.PilhaObjUtil;
import vougth.api.util.TxtAdapter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PilhaFilaService {
    @Autowired private EventService eventService;
    @Autowired private UserService userService;

    public List<Event> findByQtty(Integer qtty) {
        List<Event> events = eventService.findAllEvents();
        List<Event> lastEvents = new ArrayList<>();
        PilhaObjUtil<Event> lastRecordsStack = new PilhaObjUtil<>(events.size());

        try {
            if (!events.isEmpty()) {
                if (events.size() <= qtty) return events;
                for (int i = events.size() - 1; i > events.size() - qtty - 1; i--) lastRecordsStack.push(events.get(i));
                for (int i = 0; i < qtty; i++) lastEvents.add(lastRecordsStack.pop());

                return lastEvents;
            }
            return events;
        } catch (EventNotFoundException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public void recordTxtFile() {
        List<Event> events = eventService.findAllEvents();
        List<User> users = userService.findAll();

        try {
            if (!events.isEmpty()) {
                int counterRegisters = 0;
                String header = "eventos e usuarios ";
                Date today = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                header += dateFormat.format(today);
                header += "01";

                TxtAdapter.gravaRegistro(header, "eventos-usuarios.txt");

                for (Event event : events) {
                    String body = formatEventsDataToTxtFile(event);
                    counterRegisters++;
                    TxtAdapter.gravaRegistro(body, "eventos-usuarios.txt");
                }

                for (User user : users) {
                    String body = formatUsersDataToTxtFile(user);
                    counterRegisters++;
                    TxtAdapter.gravaRegistro(body, "eventos-usuarios.txt");
                }

                String trailer = "01 ";
                trailer += String.format("%010d", counterRegisters);
                TxtAdapter.gravaRegistro(trailer, "eventos-usuarios.txt");

            }
        } catch (EventNotFoundException | EventNotExistsException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public void getEventsTxt(HttpServletResponse response) throws IOException {
        List<Event> eventList = eventService.findAllEvents();
        List<User> userList = userService.findAll();

        ListObjUtil<Event> eventListObjUtil = new ListObjUtil<>(eventList.size());
        ListObjUtil<User> userListObjUtil = new ListObjUtil<>(userList.size());

        try {
            for (int i = 0; i < eventService.count(); i++) eventListObjUtil.adicionar(eventList.get(i));
            for (int i = 0; i < userService.count(); i++) userListObjUtil.adicionar(userList.get(i));

            TxtAdapter.downloadTxtEvent(response.getWriter(), eventListObjUtil, userListObjUtil);
            response.setHeader(
                    "Content-type: application/force-download, Content-Disposition",
                    "attachment; filename=" + "eventos-usuarios.txt");
            response.setStatus(200);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public List<Event> findByQttyFila(Integer qtty) {
        List<Event> events = eventService.findAllEvents();
        List<Event> lastEvents = new ArrayList<>();
        FilaObjUtil<Event> lastRecordsQueue = new FilaObjUtil<>(events.size());

        try {
            if (!events.isEmpty()) {
                if (events.size() <= qtty) return events;
                for (int i = events.size() - 1; i > events.size() - qtty - 1; i--) lastRecordsQueue.insert(events.get(i));
                for (int i = 0; i < qtty; i++) lastEvents.add(lastRecordsQueue.poll());

                return lastEvents;
            }
            return events;
        } catch (EventNotFoundException ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    // métodos auxiliares
    public String formatEventsDataToTxtFile(Event event) {
        String body = "02 ";
        body += String.format("%03d ", event.getIdEvent());
        body += String.format("%-40.40s", event.getNameEvent());
        body += String.format("%-10.10s", event.getCep());
        body += String.format("%-10.10s", event.getCategoryEvent());
        body += String.format("%-50.50s", event.getAddressEvent());
        body += "\n";
        body += String.format("%-25.25s", event.getDescription());
        body += String.format("%-30.30s", event.getCity());
        body += String.format("%-20.20s", event.getState());
        body += String.format("%-20.20s", event.getStartData());
        body += String.format("%-20.20s", event.getEndData());

        return body;
    }

    public String formatUsersDataToTxtFile(User user) {
        String body = "02 ";
        body += String.format("%03d ", user.getIdUser());
        body += String.format("%-40.40s", user.getUserName());
        body += String.format("%-40.40s", user.getEmail());
        body += String.format("%-10.10s", user.getNickname());
        body += String.format("%-20.20s", user.getCpf());
        body += "\n";
        body += String.format("%-15.15s", user.getTelefone());
        body += String.format("%-20.20s", user.getCep());
        body += String.format("%-10.10s", user.isOrganize());

        return body;
    }
}
