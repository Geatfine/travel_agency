package fr.lernejo.prediction;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PredictionEngineClient {
    @GET
    Call<Object> predictionEngineClient();
}
