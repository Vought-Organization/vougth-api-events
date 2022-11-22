package vougth.api.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class EventTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEventTicket;

    @CreationTimestamp // grava a data e hora atual do sistema no campo
    private LocalDateTime dataHoraAvaliacao;

    @ManyToOne
    private Event event;

    @ManyToOne
    private Ticket ticket;

    private boolean approved;

    public Integer getIdEventTicket() {
        return idEventTicket;
    }

    public void setIdEventTicket(Integer idEventTicket) {
        this.idEventTicket = idEventTicket;
    }

    public LocalDateTime getDataHoraAvaliacao() {
        return dataHoraAvaliacao;
    }

    public void setDataHoraAvaliacao(LocalDateTime dataHoraAvaliacao) {
        this.dataHoraAvaliacao = dataHoraAvaliacao;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
