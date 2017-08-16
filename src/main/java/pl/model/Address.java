package pl.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    public Address(String country, String city, double latitude, double longtitude) {
        this.country = country;
        this.city = city;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public Address() {
    }

    public Address(String country, String city, int rangeInKm) {
        this.country = country;
        this.city = city;
        this.rangeInKm = rangeInKm;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRangeInKm() {
        return rangeInKm;
    }

    public void setRangeInKm(int rangeInKm) {
        this.rangeInKm = rangeInKm;
    }
}
