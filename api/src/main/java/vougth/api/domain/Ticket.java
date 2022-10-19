package vougth.api.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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
    private Event event;

    public Integer getIdIngresso() {
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

    public UUID getUuid() {
        return ticketCode;
    }

    public void setUuid(UUID uuid) {
        this.ticketCode = uuid;
    }
}
