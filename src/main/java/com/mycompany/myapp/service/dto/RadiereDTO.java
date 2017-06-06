package com.mycompany.myapp.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycompany.myapp.domain.auto.Car;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;

/**
 * RadiereDTO
 */
public class RadiereDTO {

    private Long id;

    private String name;

    private String description;

    private String nr_inregistrare;

    private LocalDate data;

    private String motivul;

    private Car car;

    public RadiereDTO(){}

    public RadiereDTO(Long id, String name, String description, String nr_inregistrare, LocalDate data, String motivul, Car car) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.nr_inregistrare = nr_inregistrare;
        this.data = data;
        this.motivul = motivul;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNr_inregistrare() {
        return nr_inregistrare;
    }

    public void setNr_inregistrare(String nr_inregistrare) {
        this.nr_inregistrare = nr_inregistrare;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getMotivul() {
        return motivul;
    }

    public void setMotivul(String motivul) {
        this.motivul = motivul;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "RadiereDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", nr_inregistrare='" + nr_inregistrare + '\'' +
            ", data=" + data +
            ", motivul='" + motivul + '\'' +
            ", car=" + car +
            '}';
    }
}
