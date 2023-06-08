package vougth.api.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "events")
public class Event {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvent;
    @Column(name = "cep")
    private String cep;
    @Column(name = "name_event")
    private String nameEvent;
    @Column(name = "profile_photo")
    private String photoProfile;
    @Column(name = "category_event")
    private String category;
    @Column(name = "description")
    private String description;
    @Column(name = "latitude")
    private String latitude;
    @Column(name = "longitude")
    private String longitude;
    @Column(name = "address_event")
    private String addressEvent;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;
    @Column(name = "startData", nullable = false)
    private String startData;
    @Column(name = "endData", nullable = false)
    private String endData;
}
