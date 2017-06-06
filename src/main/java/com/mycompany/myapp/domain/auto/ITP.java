package com.mycompany.myapp.domain.auto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * ITP Registrul Auto Roman
 */
@Entity
@Table(name = "itp_auto")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ITP implements Serializable {

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

    @Column(name = "not_before")
    private LocalDate not_before;

    @Column(name = "not_after")
    private LocalDate not_after;

    @Column(name = "nr_inregistrare")
    private String nr_inregistrare;

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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getObservatii() {
        return observatii;
    }

    public void setObservatii(String observatii) {
        this.observatii = observatii;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ITP)) {
            return false;
        }
        ITP that = (ITP) o;
        if (!not_before.equals(that.not_before)) return false;
        if (!not_after.equals(that.not_after)) return false;
        return nr_inregistrare.equals(that.nr_inregistrare);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        /*result = 31 * result + not_after.hashCode();
        result = 31 * result + nr_inregistrare.hashCode();*/
        return result;
    }

    @Override
    public String toString() {
        return "ITP{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", observations='" + observatii + '\'' +
            ", not_before=" + not_before +
            ", not_after=" + not_after +
            ", nr_inregistrare='" + nr_inregistrare + '\'' +
            '}';
    }
}

