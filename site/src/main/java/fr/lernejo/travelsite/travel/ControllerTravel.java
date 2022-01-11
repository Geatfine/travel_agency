package fr.lernejo.travelsite.travel;

import fr.lernejo.travelsite.inscription.Inscription;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ControllerTravel {

    private final ArrayList<Inscription> inscriptionList;


    public ControllerTravel(ArrayList<Travel> travelList) {
        this.inscriptionList = new ArrayList<>();
    }


    @PostMapping("/api/inscription")
    public void apiInscription(@RequestBody Inscription inscription){
        inscriptionList.add(inscription);
    }

    @GetMapping("/api/travels")
    public ArrayList<Travel> apiTravel(@RequestParam String userName){
        return null;
    }
}
