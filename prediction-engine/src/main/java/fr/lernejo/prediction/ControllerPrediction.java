package fr.lernejo.prediction;
import fr.lernejo.travelsite.temperature.Temperature;
import fr.lernejo.travelsite.temperature.Temperatures;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ControllerPrediction {

    @GetMapping("/api/temperature")
    public Temperatures getTempListPerCountry(@RequestParam String country){
        List<Temperature> listTemp = new ArrayList<>();
        listTemp.add(new Temperature(new SimpleDateFormat("yyyy-MM-dd").format(new Date()),new TemperatureService().getTemperature(country)));
        listTemp.add(new Temperature(LocalDate.now().minusDays(1).toString(), new TemperatureService().getTemperature(country)));
        return new Temperatures(country,listTemp);
    }
}
