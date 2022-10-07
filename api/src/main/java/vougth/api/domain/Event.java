package vougth.api.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(name = "cep", nullable = false)
    private String cep;
    @Column(name = "name_event", nullable = false)
    private String nameEvent;
    @Column(name = "category_event", nullable = false)
    private String categoryEvent;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "latitude", nullable = false)
    private String latitude;
    @Column(name = "longitude", nullable = false)
    private String longitude;
    @Column(name = "address_event", nullable = false)
    private String addressEvent;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "state", nullable = false)
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

    public String getCategory() {
        return categoryEvent;
    }

    public void setCategory(String category) {
        this.categoryEvent = category;
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

    public String getAddress() {
        return addressEvent;
    }

    public void setAddress(String address) {
        this.addressEvent = address;
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
}


