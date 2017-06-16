package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.auto.Car;

import java.time.LocalDate;

/**
 * CascoDTO
 */
public class CascoDTO {

    private Long id;

    private String name;

    private String description;

    private String observatii;

    private LocalDate not_before;

    private LocalDate not_after;

    private String nr_inregistrare;

    private double sumaAsigurata;

    private String moneda;

    private String clauzeSpeciale;

    private String altePrecizari;

    private Car car;

    public CascoDTO() {
    }

    public CascoDTO(Long id, String name, String description, String observatii,
                  LocalDate not_before, LocalDate not_after, double sumaAsigurata,
                  String nr_inregistrare, String moneda, String clauzeSpeciale,
                   String altePrecizari, Car car) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.observatii = observatii;
        this.sumaAsigurata = sumaAsigurata;
        this.moneda =  moneda;
        this.clauzeSpeciale = clauzeSpeciale;
        this.altePrecizari = altePrecizari;
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

    public String getObservatii() {
        return observatii;
    }

    public void setObservatii(String observatii) {
        this.observatii = observatii;
    }

    public double getSumaAsigurata() {
        return sumaAsigurata;
    }

    public void setSumaAsigurata(long sumaAsigurata) {
        this.sumaAsigurata = sumaAsigurata;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getClauzeSpeciale() {
        return clauzeSpeciale;
    }

    public void setClauzeSpeciale(String clauzeSpeciale) {
        this.clauzeSpeciale = clauzeSpeciale;
    }

    public String getAltePrecizari() {
        return altePrecizari;
    }

    public void setAltePrecizari(String altePrecizari) {
        this.altePrecizari = altePrecizari;
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
        return "CascoDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", observatii='" + observatii + '\'' +
            ", not_before=" + not_before +
            ", not_after=" + not_after +
            ", nr_inregistrare='" + nr_inregistrare + '\'' +
            ", sumaAsigurata=" + sumaAsigurata +
            ", moneda='" + moneda + '\'' +
            ", clauzeSpeciale='" + clauzeSpeciale + '\'' +
            ", altePrecizari='" + altePrecizari + '\'' +
            ", car=" + car +
            '}';
    }
}
