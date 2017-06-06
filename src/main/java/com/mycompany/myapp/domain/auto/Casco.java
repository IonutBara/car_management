package com.mycompany.myapp.domain.auto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Asigurare Casco optionala pentru autovehicule
 */
@Entity
@Table(name = "asigurare_casco")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Casco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "observatii")
    private String observatii;

    @Column(name = "nr_inregistrare")
    private String nr_inregistrare;

    @Column(name = "not_before")
    private LocalDate not_before;

    @Column(name = "not_after")
    private LocalDate not_after;

    @Column(name = "suma_asigurata")
    private double sumaAsigurata;

    @Column(name = "moneda")
    private String moneda;

    @Column(name = "clauze_speciale")
    private String clauzeSpeciale;

    @Column(name = "alte_precizari")
    private String altePrecizari;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    private Car car;

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

    public String getNr_inregistrare() {
        return nr_inregistrare;
    }

    public void setNr_inregistrare(String nr_inregistrare) {
        this.nr_inregistrare = nr_inregistrare;
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

    public double getSumaAsigurata() {
        return sumaAsigurata;
    }

    public void setSumaAsigurata(double sumaAsigurata) {
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Casco)) return false;

        Casco casco = (Casco) o;

        if (!nr_inregistrare.equals(casco.nr_inregistrare)) return false;
        return car.equals(casco.car);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
       /* result = 31 * result + car.hashCode();*/
        return result;
    }

    @Override
    public String toString() {
        return "Casco{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", observatii='" + observatii + '\'' +
            ", nr_inregistrare='" + nr_inregistrare + '\'' +
            ", not_before=" + not_before +
            ", not_after=" + not_after +
            ", sumaAsigurata='" + sumaAsigurata + '\'' +
            ", moneda='" + moneda + '\'' +
            ", clauzeSpeciale='" + clauzeSpeciale + '\'' +
            ", altePrecizari='" + altePrecizari + '\'' +
            '}';
    }
}
