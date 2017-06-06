package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.auto.Car;

import java.time.LocalDate;

/**
 * RcaDTO
 */
public class RcaDTO {

    private Long id;

    private String name;

    private String description;

    private LocalDate not_before;

    private LocalDate not_after;

    private String nr_inregistrare;

    private Car car;

    public RcaDTO() {
    }

    public RcaDTO(Long id, String name, String description,
                  LocalDate not_before, LocalDate not_after,
                  String nr_inregistrare, Car car) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.not_before = not_before;
        this.not_after = not_after;
        this.nr_inregistrare = nr_inregistrare;
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

    public LocalDate getNot_before() {
        return not_before;
    }

    public void setNot_before(LocalDate not_before) {
        this.not_before = not_before;
    }

    public LocalDate getNot_after() {
        return not_after;
    }

    public void setNot_after(LocalDate not_after) {
        this.not_after = not_after;
    }

    public String getNr_inregistrare() {
        return nr_inregistrare;
    }

    public void setNr_inregistrare(String nr_inregistrare) {
        this.nr_inregistrare = nr_inregistrare;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "RcaDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", not_before=" + not_before +
            ", not_after=" + not_after +
            ", nr_inregistrare='" + nr_inregistrare + '\'' +
            ", car=" + car +
            '}';
    }
}
