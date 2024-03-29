package vougth.api.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIngresso;
    @Column(name = "preco_ingresso", nullable = false)
    private Double precoIngresso;
    @Column(name = "code_ticket", nullable = false)
    private UUID ticketCode = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "EventIdEvent")
    private Event event;

    public UUID getTicketCode() {
        return ticketCode;
    }

    public void setTicketCode(UUID ticketCode) {
        this.ticketCode = ticketCode;
    }


    public Integer getIdTicket() {
        return idIngresso;
    }

    public void setIdIngresso(Integer idIngresso) {
        this.idIngresso = idIngresso;
    }

    public Double getPrecoIngresso() {
        return precoIngresso;
    }

    public void setPrecoIngresso(Double precoIngresso) {
        this.precoIngresso = precoIngresso;
    }

    public void setIdTicket(Integer idTicket) {
        this.idIngresso = idTicket;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
