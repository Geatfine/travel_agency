package fr.lernejo.travelsite;

import fr.lernejo.travelsite.temperature.Temperatures;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PredictionEngineClient {
    @GET("/api/temperature")
    Call<Temperatures> getTempListPerCountry(@Query("country")String country);
}
