package com.mycompany.myapp.domain.auto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * Class Radiere auto valabila doar pentru autovehiculele
 * care sunt scoase din circulatie
 */
@Entity
@Table(name = "radiere_auto")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RadiereAuto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "nr_inregistrare")
    private String nr_inregistrare;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "motivul")
    private String motivul;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RadiereAuto)) return false;

        RadiereAuto that = (RadiereAuto) o;

        if (!id.equals(that.id)) return false;
        return nr_inregistrare.equals(that.nr_inregistrare);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + nr_inregistrare.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RadiereAuto{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", nr_inregistrare='" + nr_inregistrare + '\'' +
            ", data=" + data +
            ", motivul='" + motivul + '\'' +
            '}';
    }
}
