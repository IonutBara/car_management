package com.mycompany.myapp.domain.auto;

import com.mycompany.myapp.domain.User;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "jhi_car")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "marca")
    private String marca;

    @Column(name = "model")
    private String model;

    @Column(name = "versiune")
    private String versiune;

    @Column(name = "an_fabricatie")
    private String anFabricatie;

    @Column(name = "capacitate_cilindrica")
    private String capacitateCilindrica;

    @Column(name = "combustibil")
    private String combustibil;

    @Column(name = "cutie_viteza")
    private String cutieViteza;

    @Column(name = "transmisie")
    private String transmisie;

    @Column(name = "caroserie")
    private String caroserie;

    @Column(name = "culoare")
    private String culoare;

    @Column(name = "putere")
    private String putere;

    @Column(name = "nr_inmatriculare")
    private String nrInmatriculare;

/*    @ManyToOne
    @JoinColumn(name = "address_id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Address address;*/

    @Column(name = "nr_km")
    private double nrKm;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "car", fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<AsigurareRCA> rca = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "car", fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ITP> itp = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "car", fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Casco> casco = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "car", cascade = CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private RadiereAuto radiere;

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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVersiune() {
        return versiune;
    }

    public void setVersiune(String versiune) {
        this.versiune = versiune;
    }

    public String getAnFabricatie() {
        return anFabricatie;
    }

    public void setAnFabricatie(String anFabricatie) {
        this.anFabricatie = anFabricatie;
    }

    public String getCapacitateCilindrica() {
        return capacitateCilindrica;
    }

    public void setCapacitateCilindrica(String capacitateCilindrica) {
        this.capacitateCilindrica = capacitateCilindrica;
    }

    public String getCombustibil() {
        return combustibil;
    }

    public void setCombustibil(String combustibil) {
        this.combustibil = combustibil;
    }

    public String getCutieViteza() {
        return cutieViteza;
    }

    public void setCutieViteza(String cutieViteza) {
        this.cutieViteza = cutieViteza;
    }

    public String getTransmisie() {
        return transmisie;
    }

    public void setTransmisie(String transmisie) {
        this.transmisie = transmisie;
    }

    public String getCaroserie() {
        return caroserie;
    }

    public void setCaroserie(String caroserie) {
        this.caroserie = caroserie;
    }

    public String getCuloare() {
        return culoare;
    }

    public void setCuloare(String culoare) {
        this.culoare = culoare;
    }

    public String getPutere() {
        return putere;
    }

    public void setPutere(String putere) {
        this.putere = putere;
    }

    public String getNrInmatriculare() {
        return nrInmatriculare;
    }

    public void setNrInmatriculare(String nrInmatriculare) {
        this.nrInmatriculare = nrInmatriculare;
    }

    public Set<AsigurareRCA> getRca() {
        return rca;
    }

    public void setRca(Set<AsigurareRCA> rca) {
        this.rca = rca;
    }

    public Set<ITP> getItp() {
        return itp;
    }

    public void setItp(Set<ITP> itp) {
        this.itp = itp;
    }

    public Set<Casco> getCasco() {
        return casco;
    }

    public void setCasco(Set<Casco> casco) {
        this.casco = casco;
    }

    public double getNrKm() {
        return nrKm;
    }

    public void setNrKm(double nrKm) {
        this.nrKm = nrKm;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RadiereAuto getRadiere() {
        return radiere;
    }

    public void setRadiere(RadiereAuto radiere) {
        this.radiere = radiere;
    }

    /*    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }*/


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;

        Car car = (Car) o;

        if (!id.equals(car.id)) return false;
        return user.equals(car.user);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
       // result = 31 * result + user.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", marca='" + marca + '\'' +
            ", model='" + model + '\'' +
            ", versiune='" + versiune + '\'' +
            ", nrInmatriculare='" + nrInmatriculare + '\'' +
            ", anFabricatie='" + anFabricatie + '\'' +
            ", capacitateCilindrica='" + capacitateCilindrica + '\'' +
            ", combustibil='" + combustibil + '\'' +
            ", cutieViteza='" + cutieViteza + '\'' +
            ", transmisie='" + transmisie + '\'' +
            ", caroserie='" + caroserie + '\'' +
            ", culoare='" + culoare + '\'' +
            ", putere='" + putere + '\'' +
            ", nrKm=" + nrKm +
            '}';
    }
}
