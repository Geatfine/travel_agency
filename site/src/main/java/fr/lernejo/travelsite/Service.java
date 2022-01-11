package fr.lernejo.travelsite;

import fr.lernejo.travelsite.temperature.Temperature;
import fr.lernejo.travelsite.temperature.Temperatures;
import retrofit2.http.GET;

import java.io.IOException;

@org.springframework.stereotype.Service
public record Service(PredictionEngineClient pec) {

    public Temperatures getTemperature(String country){

        try {
            Object o = pec.getTemperature(country).execute().body();
            if(o != null && o.getClass().equals(Temperatures.class))
                return (Temperatures)o;

            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
