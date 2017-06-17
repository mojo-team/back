package fr.mojope.hackathon.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by christophe on 16/06/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Salle {

    public String identifiantSalle;
    public String nom;
    public String[] cheminPhoto;
    public int prix;
    public String description;
    public int notation;
    public int nombrePlaces;
    public Geolocalisation adresse;
    public String agencement;

    public Salle() {

    }

    public Salle(String identifiantSalle, String nom, String[] cheminPhoto, int prix, String description, int notation, int nombrePlaces, Geolocalisation adresse, String agencement) {
        this.identifiantSalle = identifiantSalle;
        this.nom = nom;
        this.cheminPhoto = cheminPhoto;
        this.prix = prix;
        this.description = description;
        this.notation = notation;
        this.nombrePlaces = nombrePlaces;
        this.adresse = adresse;
        this.agencement = agencement;
    }
}
