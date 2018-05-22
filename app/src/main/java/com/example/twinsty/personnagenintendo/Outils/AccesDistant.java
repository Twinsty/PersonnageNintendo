package com.example.twinsty.personnagenintendo.Outils;

import android.util.Log;

import com.example.twinsty.personnagenintendo.Controleur.Controle;
import com.example.twinsty.personnagenintendo.Metiers.Joueur;
import com.example.twinsty.personnagenintendo.Metiers.Personnage;
import com.example.twinsty.personnagenintendo.Metiers.Question;
import com.example.twinsty.personnagenintendo.Metiers.Reponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Twinsty on 05/05/2018.
 */

public class AccesDistant implements AsyncResponse{

    /**
     * Constante
     */
    private static final String SERVERADDR = "http://personintendo.000webhostapp.com/serveurPersonnage.php";
    private Controle controle;

    /**
     * Constructeur
     */
    public AccesDistant(){
        controle = Controle.getInstance(null);
    }
    @Override
    /**
     * Retour du serveur distant
     */
    public void processFinish(String output) {
        Log.d("Serveur", "*************" + output);
        //Découpage du message reçu %
        String[] message = output.split("%");
        //Dans message[0] : "enreg", "mario", "erreur"
        //Dans message[1] : reste du message*
        if(message.length>1){
            if(message[0].equals("enregJ")){
               Log.d("enregJ", "**********"+ message[1]);
            } else if (message[0].equals("Personnage")){
                Log.d("Personnage", "**********"+ message[1]);
                try {
                    JSONObject info = new JSONObject(message[1]);
                    Integer id = info.getInt("id");
                    String nom = info.getString("nom");
                    String origine = info.getString("origine");
                    String sexe = info.getString("sexe");
                    String espece = info.getString("espece");
                    String physique = info.getString("physique");
                    String arme = info.getString("arme");
                    String pouvoirs = info.getString("pouvoirs");
                    String ennemi = info.getString("ennemi");
                    Personnage personnage = new Personnage(id,nom,origine,sexe,espece,physique, arme, pouvoirs, ennemi);
                    controle.setPerso(personnage);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else if (message[0].equals("ChercheJoueur")){
                Log.d("ChercheJoueur", "**********"+ message[1]);
                try {
                    JSONObject info = new JSONObject(message[1]);
                    Integer id = info.getInt("id");
                    String pseudo = info.getString("pseudo");
                    Joueur j = new Joueur(id, pseudo);
                    Log.e("Joueur","************************************** " + j);
                    controle.setLeJoueur(j);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }  else if(message[0].equals("Reponses")){
                Log.e("Reponses", "********************"+ message[1]);
                try {
                    JSONArray jSonInfo = new JSONArray(message[1]);
                    ArrayList<Reponse> lesReponses = new ArrayList<Reponse>();
                    for(int i = 0; i<jSonInfo.length(); i++){
                        JSONObject info1 = new JSONObject(jSonInfo.getJSONObject(i).toString());
                        Integer id = info1.getInt("id_r");
                        String libelle = info1.getString("libelle");
                        Integer valid = info1.getInt("valid");
                        Integer question = info1.getInt("question");
                        Reponse r = new Reponse(id, question, libelle, valid);
                        lesReponses.add(r);
                    }
                    Log.w("List", "************************ "+lesReponses);
                    controle.setLesReponses(lesReponses);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if(message[0].equals("TousLesJoueurs")){
                Log.d("TousLesJoueurs", "********************"+ message[1]);
                try {
                    JSONArray jSonInfo = new JSONArray(message[1]);
                    ArrayList<Joueur> lesJoueurs = new ArrayList<Joueur>();
                    for(int i = 0; i<jSonInfo.length(); i++){
                        JSONObject info = new JSONObject(jSonInfo.getJSONObject(i).toString());
                        String pseudo = info.getString("pseudo");
                        Joueur joueur = new Joueur(pseudo);
                        lesJoueurs.add(joueur);
                    }
                    controle.setLesJoueurs(lesJoueurs);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if(message[0].equals("LesQuestions")){
                Log.d("LesQuestions", "********************"+ message[1]);
                try {
                    JSONArray jSonInfo = new JSONArray(message[1]);
                    ArrayList<Question> lesQuestions = new ArrayList<Question>();
                    for(int i = 0; i<jSonInfo.length(); i++){
                        JSONObject info = new JSONObject(jSonInfo.getJSONObject(i).toString());
                        Integer id = info.getInt("id");
                        String libelle = info.getString("libelle");
                        Question question = new Question(libelle, id);
                        lesQuestions.add(question);
                    }
                    controle.setLesQuestions(lesQuestions);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if(message[0].equals("EnrejPartie")){
                Log.e("Enregistrement partie","******************************* " + message[1]);
            } else {
                Log.e("Erreur", "**********"+ message[1]);
            }
        }
    }

    /**
     * Envoi dans la méthode AccesHTTP pour l'envoyer dans la DB
     * @param operation
     * @param lesDonneesJSON
     */
    public void envoi(String operation, JSONArray lesDonneesJSON){
        AccesHTTP accesDonnees = new AccesHTTP();

        //lien de délégation
        accesDonnees.delegate = this;
        //Ajout paramètres
        accesDonnees.addParam("operation", operation);
        accesDonnees.addParam("lesdonnees", lesDonneesJSON.toString());
        //Appel au serveur
        accesDonnees.execute(SERVERADDR);
    }
}
