package fr.mojope.hackathon;

import fr.mojope.hackathon.pojo.CritereSimple;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by christophe on 16/06/17.
 */
@RestController
@RequestMapping("criteres")
public class ConfigurationSalle {

    @RequestMapping(value = "experiences", produces = "application/json")
    public CritereSimple[] retourneLaListeDesExperiences() {
        CritereSimple[] listeDesFormats = {
                new CritereSimple(1,"Zen"),
                new CritereSimple(2,"Concentration"),
                new CritereSimple(3,"Idéation"),
                new CritereSimple(4,"Copains"),
                new CritereSimple(5,"Team"),
        };
        return listeDesFormats;
    }

    @RequestMapping(value = "configurations", produces = "application/json")
    public CritereSimple[] retourneLaListeDesConfigurations() {
        CritereSimple[] listeDesConfiguration = {
                new CritereSimple(1,"Indifférent"),
                new CritereSimple(2,"En U"),
                new CritereSimple(3,"En réunion"),
                new CritereSimple(4,"En théatre"),
                new CritereSimple(5,"Classe"),
                new CritereSimple(6,"Cocktail")
        };
        return listeDesConfiguration;
    }





}
