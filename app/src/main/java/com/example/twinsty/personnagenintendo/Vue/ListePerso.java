package com.example.twinsty.personnagenintendo.Vue;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.twinsty.personnagenintendo.Controleur.Controle;
import com.example.twinsty.personnagenintendo.R;

import java.util.ArrayList;

/**
 * Created by casti on 11/12/2017.
 */


public class ListePerso extends ListActivity implements AdapterView.OnItemClickListener {

    /**
     * Attributs
     */
    private ArrayList<String> lesValeurs = new ArrayList<String>();
    private ListView listV;
    private Controle controle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_perso);
        init();
    }

    /**
     * Liste de tout les personnages
     */
    protected void init_liste(){
        lesValeurs.add("Mario");
        lesValeurs.add("Link");
        lesValeurs.add("Wario");
        lesValeurs.add("Toad");
        lesValeurs.add("Yoshi");
        lesValeurs.add("Pit");
        lesValeurs.add("Donkey Kong");
        lesValeurs.add("Kirby");
        lesValeurs.add("Diddy Kong");
        lesValeurs.add("Olimar");
        lesValeurs.add("Sacha");
        lesValeurs.add("Pikachu");
        lesValeurs.add("Zelda");
        lesValeurs.add("Princess Peach");
        lesValeurs.add("Bowser");
        lesValeurs.add("Luigi");
    }

    /**
     * Initialisation des liens entre les objets graphiques et les variables et appel de fonctions
     */
    protected void init(){
        this.controle = Controle.getInstance(this);
        init_liste();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lesValeurs   );
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Toast.makeText(getApplicationContext(),"ligne "+ position,Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(),"perso :  "+lesValeurs.get(position),Toast.LENGTH_LONG).show()
        //Récupération du nom du personnage
        String nom = lesValeurs.get(position);
        //Récupération de sa position
        Integer i = position;
        //Fonction qui permet d'aller chercher dans la base de donnée grâce au nom du personnage
        affichePersonnage(i,nom);
    }

    /**
     * Appel à la méthode creer personnage de la classe Controle qui permet d'accéder à tout les champs de personnage
     * @param id
     * @param nom
     */
    public void affichePersonnage(Integer id, String nom){
        this.controle.creerPersonnage(id, nom);

    }

    /**
     * Récupérer tout les éléments de la DB pour l'envoyer à la prochaine vue (DetailsPerso)
     */
    public void recupPerso(){
        //Récupérer toutes les données
        String nomPers = this.controle.getNom().toString();
        String originePers = this.controle.getOrigine().toString();
        String sexePers = this.controle.getSexe().toString();
        String especePers = this.controle.getEspece().toString();
        String physiquePers = this.controle.getPhysique().toString();
        String armePers = this.controle.getArme().toString();
        String pouvoirsPers = this.controle.getPouvoirs().toString();
        String ennemiPers = this.controle.getEnnemi().toString();
        Integer idPers = this.controle.getId()-1;
            Intent suite = new Intent(getApplicationContext(), DetailsPerso.class);
            suite.putExtra("nom", nomPers);
            suite.putExtra("origine", originePers);
            suite.putExtra("sexe", sexePers);
            suite.putExtra("espece", especePers);
            suite.putExtra("physique", physiquePers);
            suite.putExtra("arme", armePers);
            suite.putExtra("pouvoirs", pouvoirsPers);
            suite.putExtra("ennemi", ennemiPers);
            suite.putExtra("id", idPers);
            startActivity(suite);


    }
}
