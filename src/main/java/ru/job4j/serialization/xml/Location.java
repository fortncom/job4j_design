package ru.job4j.serialization.xml;


import com.sun.xml.txw2.annotation.XmlElement;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.Objects;

@XmlElement(value = "location")
public class Location {

    @XmlAttribute
    private double latitude;
    @XmlAttribute
    private double longitude;

    public Location() {
    }

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Location{"
                + "latitude=" + latitude
                + ", longitude=" + longitude
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Location location = (Location) o;
        return Double.compare(location.latitude, latitude) == 0
                && Double.compare(location.longitude, longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }
}
