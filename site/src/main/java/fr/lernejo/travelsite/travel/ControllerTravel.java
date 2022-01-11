package fr.lernejo.travelsite.travel;

import fr.lernejo.travelsite.Service;
import fr.lernejo.travelsite.inscription.Inscription;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ControllerTravel {

    private final ArrayList<Inscription> inscriptionList;
    private final Service service;

    public ControllerTravel(Service service) {
        this.inscriptionList = new ArrayList<>();
        this.service = service;
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
