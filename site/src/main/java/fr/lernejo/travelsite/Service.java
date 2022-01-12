package fr.lernejo.travelsite;

import fr.lernejo.travelsite.inscription.Inscription;
import fr.lernejo.travelsite.temperature.Temperature;
import fr.lernejo.travelsite.temperature.TemperatureExpectation;
import fr.lernejo.travelsite.temperature.Temperatures;
import fr.lernejo.travelsite.travel.Travel;
import org.springframework.boot.autoconfigure.integration.IntegrationDataSourceScriptDatabaseInitializer;
import retrofit2.http.GET;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@org.springframework.stereotype.Service
public class Service {

    final PredictionEngineClient pec;

    public Service(PredictionEngineClient pec) {
        this.pec = pec;
    }

    public Temperatures getTemperature(String country){
        try {

            return pec.getTempListPerCountry(country).execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Travel> getTravels(Inscription inscription) {
        List<String> pays = getCountryList();
        Double tempUser = calculeMoyTemp(getTemperature(inscription.userCountry()));
        List<Temperatures> listTemperatures = null;
        if (pays != null)
            listTemperatures = getCountrysTemp(pays);
        List<Travel> returnList = new ArrayList<>();
        if (listTemperatures != null && tempUser != null)
            for (Temperatures t : listTemperatures){
                Travel trav = checkMinimumTemperatureDistance(t, tempUser, inscription);
                if (trav != null)
                    returnList.add(trav);
            }
        return returnList;
    }
    private Travel checkMinimumTemperatureDistance(Temperatures t , double tempUser, Inscription inscription){
            if (t != null ) {
                double moyTemp = calculeMoyTemp(t);
                if (inscription.weatherExpectation().equals(TemperatureExpectation.COLDER.toString()))
                    if (tempUser - moyTemp >= inscription.minimumTemperatureDistance())
                        return new Travel(t.country, moyTemp);
                    else
                    if (tempUser - moyTemp <= -1 * inscription.minimumTemperatureDistance())
                        return new Travel(t.country, moyTemp);
            }
            return null;
    }
    private List<String> getCountryList() {
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("countries.txt");
            String content = null;
            content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            Stream<String> lines = content.lines();
            List<String> pays  = lines.filter(Objects::nonNull).toList();
            return pays;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }


    private Double calculeMoyTemp(Temperatures temp){
        Double moy = null;
        if(temp!=null) {
            moy = 0.0;
            for (Temperature t : temp.temperatures)
                moy += t.temperature;
            moy /= 2;
        }
        return moy;
    }

    private List<Temperatures> getCountrysTemp(List<String> pays){
        List<Temperatures> listTemp = new ArrayList<>();
        for (String s: pays) {
            Temperatures temp = getTemperature(s);
            if (temp != null)
                listTemp.add(temp);
        }
        return listTemp;
    }
}
