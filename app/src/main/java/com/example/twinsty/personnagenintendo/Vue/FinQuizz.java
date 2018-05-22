package com.example.twinsty.personnagenintendo.Vue;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.twinsty.personnagenintendo.Controleur.Controle;
import com.example.twinsty.personnagenintendo.R;

public class FinQuizz extends AppCompatActivity {
    Controle controle;
    TextView p;
    TextView s;
    Button enrej;
    Button retour;
    Integer leScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_quizz);
        Bundle extras = getIntent().getExtras();
        this.controle = Controle.getInstance(this);
        final String lePseudo = extras.getString("pseudo");
        leScore = extras.getInt("score");

        p = (TextView)findViewById(R.id.pseudo);
        s = (TextView)findViewById(R.id.score);
        enrej = (Button)findViewById(R.id.enrej);
        retour = (Button)findViewById(R.id.retour);

        p.setText(lePseudo);
        s.setText("Vous avez eu " + leScore + " sur 1000 points.");

        enrej.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Récuperer le joueur dans la base de donnée
                controle.recupJoueur(lePseudo);
                //Enregistrer ses résultats.
            }
        });

        retour.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent suite = new Intent(getApplicationContext(), Accueil.class);
                startActivity(suite);
            }
        });
    }

    public void recupJ(){
        String id = controle.getIdJ().toString();
        String pseudo = controle.getPseudo().toString();
        controle.enrejPartie(id, leScore);
        enrej.setClickable(false);
        enrej.setBackgroundColor(Color.LTGRAY);
    }
}
