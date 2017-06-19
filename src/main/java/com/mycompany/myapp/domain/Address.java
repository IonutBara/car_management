package com.mycompany.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * UserUnauthorizedException*/


@Entity
@Table(name = "address")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "zip_code")
    private String zipCode;

    @ManyToOne//(cascade = CascadeType.ALL, targetEntity = Country.class)
    @JoinColumn(name = "country_id")
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Country country;

/*    @JsonIgnore
    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();*/

/*    @JsonIgnore
    @OneToMany(mappedBy = "address")
    private Set<Car> companies = new HashSet<>();*/

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

/*    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }*/


/*    public Set<Car> getCar() {
        return companies;
    }

    public void setCar(Set<Car> companies) {
        this.companies = companies;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (!city.equals(address.city)) return false;
        return country.equals(address.country);
    }

    @Override
    public int hashCode() {
        int result = city.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
            "id=" + id +
            ", city='" + city + '\'' +
            ", zipCode='" + zipCode + '\'' +
            ", country=" + country +
           // ", users=" + users +
           // ", companies=" + companies +
            '}';
    }
}
