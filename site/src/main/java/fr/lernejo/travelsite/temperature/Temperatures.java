package fr.lernejo.travelsite.temperature;

import java.util.List;

public record Temperatures(String country, List<Temperature> temperatures) {
}
