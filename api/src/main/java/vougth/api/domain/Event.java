package vougth.api.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEvent;
    @Column(name = "cep")
    private String cep;
    @Column(name = "name_event")
    private String nameEvent;

    @Column(name = "profile_photo")
    private String photoProfile;

    @Column(name = "category_event")
    private String categoryEvent;
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
    private LocalDateTime startData;
    @Column(name = "endData", nullable = false)
    private LocalDateTime endData;

    public Integer getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Integer idEvent) {
        this.idEvent = idEvent;
    }

    public String getCategoryEvent() {
        return categoryEvent;
    }

    public void setCategoryEvent(String categoryEvent) {
        this.categoryEvent = categoryEvent;
    }

    public String getAddressEvent() {
        return addressEvent;
    }

    public void setAddressEvent(String addressEvent) {
        this.addressEvent = addressEvent;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDateTime getStartData() {
        return startData;
    }

    public void setStartData(LocalDateTime startData) {
        this.startData = startData;
    }

    public LocalDateTime getEndData() {
        return endData;
    }

    public void setEndData(LocalDateTime endData) {
        this.endData = endData;
    }

    public String getPhotoProfile() {
        return photoProfile;
    }

    public void setPhotoProfile(String photoProfile) {
        this.photoProfile = photoProfile;
    }
}


