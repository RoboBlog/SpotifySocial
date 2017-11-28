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

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", rangeInKm=" + rangeInKm +
                ", latitude=" + latitude +
                ", longtitude=" + longtitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (rangeInKm != address.rangeInKm) return false;
        if (Double.compare(address.latitude, latitude) != 0) return false;
        if (Double.compare(address.longtitude, longtitude) != 0) return false;
        if (id != null ? !id.equals(address.id) : address.id != null) return false;
        if (country != null ? !country.equals(address.country) : address.country != null) return false;
        return city != null ? city.equals(address.city) : address.city == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + rangeInKm;
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longtitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
