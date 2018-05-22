package com.example.twinsty.personnagenintendo.Metiers;

/**
 * Created by Twinsty on 05/05/2018.
 */

public class Reponse {
    /**
     * Attributs
     */
    Integer re_id;
    Integer qu_id;
    String reponse;
    Integer valid;

    /**
     * Constructeur
     * @param re_id
     * @param qu_id
     * @param reponse
     * @param valid
     */
    public Reponse(Integer re_id, Integer qu_id, String reponse, Integer valid) {
        this.re_id = re_id;
        this.qu_id = qu_id;
        this.reponse = reponse;
        this.valid = valid;
    }

    /**
     * Getter de l'id de la question de la réponse
     * @return
     */
    public Integer getQu_id() { return qu_id; }

    /**
     * Getter du libellé de la réponse
     * @return
     */
    public String getReponse() {
        return reponse;
    }

    /**
     * Getter du valid de la réponse
     * @return
     */
    public Integer getValid() { return valid; }
}
