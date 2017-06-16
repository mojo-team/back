package fr.mojope.hackathon;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by christophe on 16/06/17.
 */
@RestController
@RequestMapping("criteres")
public class ConfigurationSalle {

    @RequestMapping(value = "formats", produces = "application/json")
    public Critere[] retourneLaListeDesFormats() {
        Critere[] listeDesFormats = {
                new Critere(1,"Indifférent"),
                new Critere(2,"Location de salle uniquement"),
                new Critere(3,"Journée d’étude"),
                new Critere(4,"Séminaire résidentiel"),
                new Critere(5,"Bureau seul"),
                new Critere(6,"Visio-conférence")
        };
        return listeDesFormats;
    }

    @RequestMapping(value = "configurations", produces = "application/json")
    public Critere[] retourneLaListeDesConfigurations() {
        Critere[] listeDesConfiguration = {
                new Critere(1,"Indifférent"),
                new Critere(2,"En U"),
                new Critere(3,"En réunion"),
                new Critere(4,"En théatre"),
                new Critere(5,"Classe"),
                new Critere(6,"Cocktail")
        };
        return listeDesConfiguration;
    }

    @RequestMapping(value = "equipements", produces = "application/json")
    public Critere[] retourneLaListeDesEquipements() {
        Critere[] listeDesEquipements = {
                new Critere(1,"Aucun"),
                new Critere(2,"Accès WIFI"),
                new Critere(3,"Bloc notes / Stylos"),
                new Critere(4,"Climatisation"),
                new Critere(5,"Ecran de projection"),
                new Critere(6,"Paper board"),
                new Critere(7,"Photocopieur"),
                new Critere(8,"Rétroprojecteur"),
                new Critere(9,"Vestiaire"),
                new Critere(10,"Vidéo projecteur"),
                new Critere(11,"Visioconférence")
        };
        return listeDesEquipements;
    }



}
