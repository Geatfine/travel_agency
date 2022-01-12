package fr.lernejo.travelsite.temperature;

import java.util.List;

public class Temperatures {
    public final String country;
    public final List<Temperature> temperatures;

    public Temperatures(String country, List<Temperature> temperatures) {
        this.country = country;
        this.temperatures = temperatures;
    }
}
