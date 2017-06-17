package fr.mojope.hackathon.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by christophe on 16/06/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CritereSimple {

    public int code;
    public String libelle;

    public CritereSimple() {}

    public CritereSimple(int code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }
}
