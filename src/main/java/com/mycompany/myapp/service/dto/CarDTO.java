package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.domain.auto.Car;

import javax.validation.constraints.Size;

/**
 * DTO represent a Car with all information
 */
public class CarDTO {

    private Long id;

    @Size(max = 50)
    private String name;

    private String description;

    private String marca;

    private String model;

    private String versiune;

    private String anFabricatie;

    private String capacitateCilindrica;

    private String combustibil;

    private String cutieViteza;

    private String transmisie;

    private String caroserie;

    private String culoare;

    private String putere;

    private String nrInmatriculare;

    private double nrKm;

    private User user;

/*    private Set<AsigurareRCA> rca = new HashSet<>();
    private Set<ITP> itp = new HashSet<>();
    private Set<Casco> casco = new HashSet<>();
    private RadiereAuto radiere;*/

    public CarDTO() {
    }

    public CarDTO(Car car) {
        this.user = car.getUser();
    }

    public CarDTO(String name, String description,
                  String marca, String model,
                  String versiune, String anFabricatie,
                  String capacitateCilindrica, String combustibil,
                  String cutieViteza, String transmisie,
                  String caroserie, String culoare,
                  String putere, String nrInmatriculare,
                  double nrKm, User user) {
        this.name = name;
        this.description = description;
        this.marca = marca;
        this.model = model;
        this.versiune = versiune;
        this.anFabricatie = anFabricatie;
        this.capacitateCilindrica = capacitateCilindrica;
        this.combustibil = combustibil;
        this.cutieViteza = cutieViteza;
        this.transmisie = transmisie;
        this.caroserie = caroserie;
        this.culoare = culoare;
        this.putere = putere;
        this.nrInmatriculare = nrInmatriculare;
        this.nrKm = nrKm;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getMarca() {
        return marca;
    }

    public String getModel() {
        return model;
    }

    public String getVersiune() {
        return versiune;
    }

    public String getAnFabricatie() {
        return anFabricatie;
    }

    public String getCapacitateCilindrica() {
        return capacitateCilindrica;
    }

    public String getCombustibil() {
        return combustibil;
    }

    public String getCutieViteza() {
        return cutieViteza;
    }

    public String getTransmisie() {
        return transmisie;
    }

    public String getCaroserie() {
        return caroserie;
    }

    public String getCuloare() {
        return culoare;
    }

    public String getPutere() {
        return putere;
    }

    public String getNrInmatriculare() {
        return nrInmatriculare;
    }

    public double getNrKm() {
        return nrKm;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Car{" +
            ", id='" + id + '\'' +
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
            ", user=" + user +
            // ", rca=" + rca +
            // ", itp=" + itp +
            // ", casco=" + casco +
            // ", radiere=" + radiere +
            '}';
    }
}
