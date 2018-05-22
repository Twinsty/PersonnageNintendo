package com.example.twinsty.personnagenintendo.Metiers;

/**
 * Created by Twinsty on 05/05/2018.
 */

public class Question {
    /**
     * Attributs
     */
    private String libelle;
    private Integer qu_id;

    /**
     * Constructeur
     * @param libelle
     * @param qu_id
     */
    public Question(String libelle, Integer qu_id) {
        this.libelle = libelle;
        this.qu_id = qu_id;
    }

    /**
     * Getter du libelle de la question
     * @return
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Getter de l'id de la question
     * @return
     */
    public Integer getQu_id() { return qu_id; }
}
