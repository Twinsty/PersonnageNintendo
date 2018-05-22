package com.example.twinsty.personnagenintendo.Controleur;

import android.content.Context;

import com.example.twinsty.personnagenintendo.Metiers.Joueur;
import com.example.twinsty.personnagenintendo.Metiers.Question;
import com.example.twinsty.personnagenintendo.Metiers.Reponse;
import com.example.twinsty.personnagenintendo.Outils.AccesDistant;
import com.example.twinsty.personnagenintendo.Metiers.Personnage;
import com.example.twinsty.personnagenintendo.Outils.Serializer;
import com.example.twinsty.personnagenintendo.Vue.FinQuizz;
import com.example.twinsty.personnagenintendo.Vue.ListePerso;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Twinsty on 05/05/2018.
 */

public final class Controle {
    /**
     * Attributs
     */
    private static Controle instance = null;
    private static Personnage personnage;
    private static Joueur joueur;
    private ArrayList<Joueur> lesJoueurs = new ArrayList<Joueur>();
    private ArrayList<Question> lesQuestions = new ArrayList<Question>();
    private static ArrayList<Reponse> lesReponses = new ArrayList<Reponse>();
    private static String nomJoueur = "saveJoueur";
    private static AccesDistant accesDistant;
    private static Context context;

    /**
     * Constructeur
     */
    private Controle() { };

    /**
     * Création de l'instance
     * @param context
     * @return
     */
    public static final Controle getInstance(Context context){
        if(context != null){
            Controle.context = context;
        }
        if (Controle.instance == null){
            Controle.instance = new Controle();
            recupSerialise(context);
            accesDistant = new AccesDistant();
            accesDistant.envoi("TousLesJoueurs", new JSONArray());
            accesDistant.envoi("LesQuestions", new JSONArray());
            accesDistant.envoi("Reponses", new JSONArray());
        }
        return Controle.instance;
    }

    /**
     * Récupérer de nouvelle questions
     */
    public void chercheQuestion(){
        accesDistant.envoi("LesQuestions", new JSONArray());
    }

    /**
     * Récupération d'un personnage de la liste via la DB
     * @param id
     * @param nom
     */
    public void creerPersonnage (Integer id, String nom){
        List laListe = new ArrayList();
        laListe.add(nom);
        accesDistant.envoi("Personnage", new JSONArray(laListe));
    }

    /**
     * Setter d'un personnage pour la classe AccesDistant (nom) et envoi dans la vue ListePerso pour la prochaine vue DetailsPerso
     * @param personnage
     */
    public void setPerso(Personnage personnage){
        Controle.personnage = personnage;
        ((ListePerso)context).recupPerso();
    }

    /**
     * Getter nom personnage
     * @return
     */
    public String getNom(){ return personnage.getPe_nom(); }

    /**
     * Getter de l'espèce personnage
     * @return
     */
    public String getEspece(){ return personnage.getPe_espece();}

    /**
     * Getter du sexe personnage
     * @return
     */
    public String getSexe(){ return personnage.getPe_sexe();}

    /**
     * Getter de l'origine du personnage
     * @return
     */
    public String getOrigine(){ return personnage.getPe_origine();}

    /**
     * Getter de l'id personnage
     * @return
     */
    public Integer getId(){ return personnage.getPe_id();}

    /**
     * Getter du physique du personnage
     * @return
     */
    public String getPhysique(){ return personnage.getPe_physique();}

    /**
     * Getter de l'arme favorite du personnage
     * @return
     */
    public String getArme(){ return personnage.getPe_arme();}

    /**
     * Getter du pouvoirs du personnage
     * @return
     */
    public String getPouvoirs(){ return personnage.getPe_pouvoirs();}

    /**
     * Getter de l'ennemi du personnage
     * @return
     */
    public String getEnnemi(){ return personnage.getPe_ennemi();}

    /**
     * Getter du pseudo du joueur
     */
    public String getPseudo(){
        if(joueur != null){
            return joueur.getPseudo();
        } else {
            return null;
        }
    }

    /**
     * Getter de l'id du joueur
     * @return
     */
    public Integer getIdJ(){
        return joueur.getJo_id();
    }

    /**
     * Getter de tout les joueurs de la base de donnée
     * @return
     */
    public ArrayList<Joueur> getLesJoueurs() {
        return lesJoueurs;
    }

    /**
     * Setter de tout les joueurs de la base (Pour la classe AccesDistant)
     * @param lesJoueurs
     */
    public void setLesJoueurs(ArrayList<Joueur> lesJoueurs) {
        this.lesJoueurs = lesJoueurs;
    }

    /**
     * Création du joueur et envoi dans la base de données / Sérialiser dans l'application
     * @param pseudo
     * @param context
     */
    public void creerJoueur(String pseudo, Context context){
        joueur = new Joueur(pseudo);
        accesDistant.envoi("enregJ", joueur.convertToJSONArray());
        Serializer.serialise(nomJoueur, joueur, context);
    }

    /**
     * Vérification si le joueur est dans la DB si non enregistrement dans la DB
     * @param pseudo
     * @param context
     */
    public void verifJoueur(String pseudo, Context context){
        Boolean valid = false;
        if(lesJoueurs.size() <= 0){
            creerJoueur(pseudo, context);
        } else {
            for(Joueur J: lesJoueurs){
                if(pseudo.equals(J.getPseudo())){
                    valid = false;
                    joueur = new Joueur(pseudo);
                    Serializer.serialise(nomJoueur, joueur, context);
                    break;
                } else {
                    valid = true;
                }
            }
            if(valid == true){
                creerJoueur(pseudo, context);
            }
        }
    }

    /**
     * Récupération de la sérialisation du joueur
     * @param context
     */
    public static void recupSerialise(Context context){ joueur = (Joueur) Serializer.deSeralise(nomJoueur, context); }

    /**
     * Récupération du joueur via le pseudo dans la DB
     * @param pseudo
     */
    public void recupJoueur(String pseudo){
        List laListe = new ArrayList();
        laListe.add(pseudo);
        accesDistant.envoi("ChercheJoueur", new JSONArray(laListe));
    }

    /**
     * Setter du joueur et envoi dans la vue FinQuizz
     * @param joueur
     */
    public void setLeJoueur(Joueur joueur){
        Controle.joueur = joueur;
        ((FinQuizz)context).recupJ();
    }

    /**
     * Enregistrement de la partie dans la DB
     * @param idJoueur
     * @param score
     */
    public void enrejPartie(String idJoueur, Integer score){
        List laListe = new ArrayList();
        laListe.add(idJoueur);
        laListe.add(score);
        accesDistant.envoi("EnrejPartie", new JSONArray(laListe));
    }

    /**
     * Setter de toutes les questions de la DB (Pour la classe AccesDistant)
     * @param lesQuestions
     */
    public void setLesQuestions(ArrayList<Question> lesQuestions) { this.lesQuestions = lesQuestions; }

    /**
     * Getter de toutes les questions de la DB
     * @return
     */
    public ArrayList<Question> getLesQuestions() { return lesQuestions; }

    /**
     * Setter de toutes les reponses de la DB
     * @param lesR
     */
    public void setLesReponses(ArrayList<Reponse> lesR){
        lesReponses.clear();
        lesReponses = lesR;
    }

    /**
     * Getter de toutes les réponses de la DB
     * @return
     */
    public ArrayList<Reponse> getLesReponses() {
        return lesReponses;
    }
}
