package com.example.twinsty.personnagenintendo.Metiers;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Twinsty on 05/05/2018.
 */

public class Joueur implements Serializable{
    /**
     * Attributs
     */
    private Integer jo_id;
    private String pseudo;

    /**
     * Constructeur
     * @param pseudo
     */
    public Joueur(String pseudo){
        this.pseudo = pseudo;
    }

    /**
     * Constructeur
     * @param jo_id
     * @param pseudo
     */
    public Joueur(Integer jo_id, String pseudo) {
        this.jo_id = jo_id;
        this.pseudo = pseudo;
    }

    /**
     * Getter le pseudo du joueur
     * @return
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Setter du pseudo du joueur
     * @param pseudo
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * Getter de l'id du joueur
     * @return
     */
    public Integer getJo_id() {
        return jo_id;
    }


    /**
     * Conversion du joueur au format JSONARRAY pour l'envoi dans la DB
     * @return
     */
    public JSONArray convertToJSONArray(){
        List laListe = new ArrayList();
        laListe.add(pseudo);
        return new JSONArray(laListe);
    }
}
