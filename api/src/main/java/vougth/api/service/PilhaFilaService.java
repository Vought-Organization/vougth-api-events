package vougth.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

@Service
public class PilhaFilaService {
    @Autowired private EventService eventService;
    @Autowired private UserService userService;

    public List<Event> findByQtty(Integer qtty) {
        List<Event> events = eventService.findAllEvents();
        List<Event> lastEvents = new ArrayList<>();
        PilhaObjUtil<Event> lastRecordsStack = new PilhaObjUtil<>(events.size());

        if (!events.isEmpty()) {
            if (events.size() <= qtty) return events;
            for (int i = events.size() - 1; i > events.size() - qtty - 1; i--) lastRecordsStack.push(events.get(i));
            for (int i = 0; i < qtty; i++) lastEvents.add(lastRecordsStack.pop());

            return lastEvents;
        }
        return events;
    }

    public void recordTxtFile() throws IOException {
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
                String nomeArq = "eventos-usuarios.txt";

                TxtAdapter.gravaRegistro(header, nomeArq);

                for (Event event : events) {
                    String body = formatEventsDataToTxtFile(event);
                    counterRegisters++;
                    TxtAdapter.gravaRegistro(body, nomeArq);
                }

                for (User user : users) {
                    String body = formatUsersDataToTxtFile(user);
                    counterRegisters++;
                    TxtAdapter.gravaRegistro(body, nomeArq);
                }

                String trailer = "01 ";
                trailer += String.format("%010d", counterRegisters);
                TxtAdapter.gravaRegistro(trailer, nomeArq);

            }
        } catch (EventNotFoundException | EventNotExistsException | IOException exception) {
            throw exception;
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
            throw ex;
        }
    }

    // métodos auxiliares
    public String formatEventsDataToTxtFile(Event event) {
        String format1010 = "%-10.10s";
        String format2020 = "%-20.20s";
        String format4040 = "%-40.40s";
        String body = "02 ";
        body += String.format("%03d ", event.getIdEvent());
        body += String.format(format4040, event.getNameEvent());
        body += String.format(format1010, event.getCep());
        body += String.format(format1010, event.getCategory());
        body += String.format("%-50.50s", event.getAddressEvent());
        body += "\n";
        body += String.format("%-25.25s", event.getDescription());
        body += String.format("%-30.30s", event.getCity());
        body += String.format(format2020, event.getState());
        body += String.format(format2020, event.getStartData());
        body += String.format(format2020, event.getEndData());

        return body;
    }

    public String formatUsersDataToTxtFile(User user) {
        String format1010 = "%-10.10s";
        String format2020 = "%-20.20s";
        String format4040 = "%-40.40s";
        String body = "02 ";

        body += String.format("%03d ", user.getIdUser());
        body += String.format(format4040, user.getUserName());
        body += String.format(format4040, user.getEmail());
        body += String.format(format1010, user.getNickname());
        body += String.format(format2020, user.getCpf());
        body += "\n";
        body += String.format("%-15.15s", user.getTelefone());
        body += String.format(format2020, user.getCep());
        body += String.format(format1010, user.isOrganize());

        return body;
    }
}
