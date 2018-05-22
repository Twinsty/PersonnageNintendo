package com.example.twinsty.personnagenintendo.Metiers;

import org.json.JSONArray;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Twinsty on 05/05/2018.
 */

public class Personnage {
    /**
     * Attributs
     */
    private Integer pe_id;
    private String pe_nom;
    private String pe_origine;
    private String pe_sexe;
    private String pe_espece;
    private String pe_physique;
    private String pe_arme;
    private String pe_pouvoirs;
    private String pe_ennemi;

    /**
     * Constructeur
     * @param id
     * @param nom
     * @param origine
     * @param sexe
     * @param espece
     * @param physique
     * @param arme
     * @param pouvoirs
     * @param ennemi
     */
    public Personnage(Integer id, String nom, String origine, String sexe, String espece, String physique, String arme, String pouvoirs, String ennemi){
        pe_id = id;
        pe_nom = nom;
        pe_origine = origine;
        pe_sexe = sexe;
        pe_espece = espece;
        pe_physique = physique;
        pe_arme = arme;
        pe_pouvoirs = pouvoirs;
        pe_ennemi = ennemi;
    }

    /**
     * Getter du nom du personnage
     * @return
     */
    public String getPe_nom() {
        return pe_nom;
    }

    /**
     * Getter de l'origine du personnage
     * @return
     */
    public String getPe_origine() {
        return pe_origine;
    }

    /**
     * Getter du sexe du personnage
     * @return
     */
    public String getPe_sexe() {
        return pe_sexe;
    }

    /**
     * Getter de l'espèce du personnage
     * @return
     */
    public String getPe_espece() {
        return pe_espece;
    }

    /**
     * Getter de l'id du personnage
     * @return
     */
    public Integer getPe_id() {
        return pe_id;
    }

    /**
     * Getter du physique du personnage
     * @return
     */
    public String getPe_physique() {
        return pe_physique;
    }

    /**
     * Getter de l'arme favorite du personnage
     * @return
     */
    public String getPe_arme() {
        return pe_arme;
    }

    /**
     * Getter du pouvoirs du personnage
     * @return
     */
    public String getPe_pouvoirs() {
        return pe_pouvoirs;
    }

    /**
     * Getter de l'ennemi du personnage
     * @return
     */
    public String getPe_ennemi() {
        return pe_ennemi;
    }
}
