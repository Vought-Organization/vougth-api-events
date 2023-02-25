package vougth.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class EventUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idEvent;
    private Integer idUser;

    public EventUser(Integer idEvent, Integer idUser) {
        this.idUser = idUser;
        this.idEvent = idEvent;
    }
}
