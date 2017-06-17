package fr.mojope.hackathon.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by christophe on 16/06/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CritereRechercheSalle {

    public Geolocalisation geoloc;
    public int contenance;
    public String dateReservation;
    public int duree;

    public int notation;

    public String dateDeCreation;

    public String identifiantSalle;

    public CritereRechercheSalle() {}

    public CritereRechercheSalle(int contenance, String dateReservation, int duree, int notation, Geolocalisation geoloc, String dateDeCreation, String identifiantSalle) {
        this.contenance = contenance;
        this.dateReservation = dateReservation;
        this.duree = duree;
        this.notation = notation;
        this.geoloc = geoloc;
        this.dateDeCreation = dateDeCreation;
        this.identifiantSalle = identifiantSalle;
    }
}
