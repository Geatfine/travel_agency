package fr.lernejo.prediction;
import fr.lernejo.travelsite.temperature.Temperature;
import fr.lernejo.travelsite.temperature.Temperatures;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

@RestController
public class ControllerPrediction {

    @GetMapping("/api/temperature")
    public Object getTempListPerCountry(@RequestParam String country){
        try {
            LocalDate today= LocalDate.now();
            ArrayList<Temperature> listTemp = new ArrayList<>();
            listTemp.add(new Temperature(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),new TemperatureService().getTemperature(country)));
            listTemp.add(new Temperature(new SimpleDateFormat("yyyy-MM-dd").format(today.minusDays(1)),new TemperatureService().getTemperature(country)));
            return new Temperatures(country,listTemp);
        }catch (UnknownCountryException e){
            return ResponseEntity.status(417).body("Unknown username (CODE 417)");
        }
    }


}
