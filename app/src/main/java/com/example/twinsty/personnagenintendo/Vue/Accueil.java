package com.example.twinsty.personnagenintendo.Vue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.twinsty.personnagenintendo.Controleur.Controle;
import com.example.twinsty.personnagenintendo.R;

public class Accueil extends AppCompatActivity {
    /**
     * Propriété
     */
    private Button apprendre;
    private Button jouer;
    private EditText pseudo = null;
    private Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        init();
    }

    /**
     * Initialisation des liens entre les objets graphiques et les variables
     */
    private void init(){
        apprendre = (Button) findViewById(R.id.apprendre);
        jouer = (Button)findViewById(R.id.jouer);
        pseudo = (EditText)findViewById(R.id.pseudo);
        this.controle = Controle.getInstance(this);
        //Récupération du joueur sérialiser si il existe
        recupJoueur();
        //Appel de la fonction pour le click du bouton Apprendre
        ecoutePersonnage();
        //Appel de la fonction pour le click du bouton Jouer
        ecouteJouer();
    }

    /**
     * Récupération du joueur si il a été séralisé
     */
    private void recupJoueur(){
        if(controle.getPseudo() != null){
            pseudo.setText(controle.getPseudo().toString());
        }
    }

    /**
     * Ecoute de l'événement sur le bouton Apprendre
     */
    private void ecoutePersonnage(){
        apprendre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent suite = new Intent(getApplicationContext(), ListePerso.class);
                startActivity(suite);
            }
        });
    }

    /**
     * Ecoute de l'événement sur le bouton Jouer
     */
    private void ecouteJouer(){
        jouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ps = pseudo.getText().toString();
                if(!ps.equals("")){
                    controle.verifJoueur(ps, getApplicationContext());
                    controle.chercheQuestion();
                    Intent suite = new Intent(getApplicationContext(), Jouer.class);
                    suite.putExtra("pseudo",ps);
                    startActivity(suite);
                } else {
                    Toast.makeText(getApplicationContext(),"Veuillez entrer un pseudo avant de lancer le quizz. ",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

