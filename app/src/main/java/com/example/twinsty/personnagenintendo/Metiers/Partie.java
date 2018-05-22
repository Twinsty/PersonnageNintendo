package com.example.twinsty.personnagenintendo.Metiers;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Twinsty on 05/05/2018.
 */

public class Partie implements Serializable{
    /**
     * Attributs
     */
    private Joueur joueur;
    private Integer pa_id;
    private Integer pa_score;

    //Pour une partie on est obligé d'être un joueur

    /**
     * Constructeur
     * @param j
     */
    public Partie(Joueur j){
        joueur = j;
        pa_score = 0;
    }

    /**
     * Constructeur
     * @param j
     * @param score
     */
    public Partie(Joueur j, Integer score){
        joueur = j;
        pa_score = score;
    }

    /**
     * Getter du joueur de la partie
     * @return
     */
    public Joueur getJoueur() {
        return joueur;
    }

    /**
     * Getter du score de la partie
     * @return
     */
    public Integer getPa_score() {
        return pa_score;
    }

    /**
     * Setter du score de la partie
     * @param pa_score
     */
    public void setPa_score(Integer pa_score) {
        this.pa_score = pa_score;
    }

    /**
     * Méthode de calcul du score
     * @return
     */
    public Integer calculScore(){
        this.pa_score+= 100;
        return pa_score;
    }

    /**
     * Conversion de la partie au format JSONARRAY
     * @return
     */
    public JSONArray convertToJSONArray(){
        List laListe = new ArrayList();
        laListe.add(joueur.getPseudo());
        laListe.add(pa_score);
        return new JSONArray(laListe);
    }
}
