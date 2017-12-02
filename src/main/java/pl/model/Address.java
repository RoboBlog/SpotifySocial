package pl.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@EqualsAndHashCode
@ToString
@Entity
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    private String country;
    private String city;
    private int rangeInKm;
    private double latitude;
    private double longtitude;

    public Address() {
    }

    public Address(String country, String city, double latitude, double longtitude) {
        this.country = country;
        this.city = city;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }


}
