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
    private Integer id_event;
    private Integer id_user;

    public EventUser(Integer id_event, Integer id_user) {
        this.id_user = id_user;
        this.id_event = id_event;
    }
}
