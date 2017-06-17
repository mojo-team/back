package fr.mojope.hackathon.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by christophe on 17/06/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Prestation {

    public int identifiant;
    public String nom;
    public int prix;

    public Prestation() {}

    public Prestation(int identifiant, String nom, int prix) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.prix = prix;
    }
}
