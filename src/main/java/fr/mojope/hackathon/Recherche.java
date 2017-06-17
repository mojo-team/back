package fr.mojope.hackathon;

import fr.mojope.hackathon.pojo.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by christophe on 16/06/17.
 */
@RestController
@RequestMapping("/recherche")
public class Recherche {

    @RequestMapping(value="/salles", produces = "application/json")
    public Salle[] retourneLaListeDesSalles(CritereRechercheSalle critere) {

        List<Salle> salles = new ArrayList<Salle>();
        salles.add(ajouteUneSalle());
        salles.add(ajouteUneSalle());
        salles.add(ajouteUneSalle());

        return salles.toArray(new Salle[salles.size()]);
    }

    private Salle ajouteUneSalle() {
        String[] images = new String[1];
        images[0] = "mon images";
        Salle salle = new Salle("salle1", "Nom de la salle 1", images, 100, "Une description de salle", 4, 10, new Geolocalisation(), "En U");
        return salle;
    }

    @RequestMapping(value="/prestations", produces = "application/json")
    public Prestation[] retourneLaListeDesPrestations(String idSalle) {
        Prestation[] listeDesPrestations = {
                new Prestation(2,"Accès WIFI", 10),
                new Prestation(3,"Bloc notes / Stylos", 5),
                new Prestation(4,"Climatisation", 20),
                new Prestation(5,"Ecran de projection", 50),
                new Prestation(6,"Paper board", 10),
                new Prestation(7,"Photocopieur", 20),
                new Prestation(8,"Rétroprojecteur", 10),
                new Prestation(9,"Vestiaire", 30),
                new Prestation(10,"Vidéo projecteur", 20),
                new Prestation(11,"Visioconférence", 50)
        };
        return listeDesPrestations;
    }
}
