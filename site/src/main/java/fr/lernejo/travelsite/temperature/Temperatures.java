package fr.lernejo.travelsite.temperature;

import java.lang.reflect.Array;
import java.util.ArrayList;

public record Temperatures(String country, ArrayList<Temperature> temperatures) {
}
