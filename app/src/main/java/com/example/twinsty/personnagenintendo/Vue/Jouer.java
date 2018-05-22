package com.example.twinsty.personnagenintendo.Vue;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.twinsty.personnagenintendo.Controleur.Controle;
import com.example.twinsty.personnagenintendo.Metiers.Joueur;
import com.example.twinsty.personnagenintendo.Metiers.Partie;
import com.example.twinsty.personnagenintendo.Metiers.Question;
import com.example.twinsty.personnagenintendo.Metiers.Reponse;
import com.example.twinsty.personnagenintendo.R;

import java.util.ArrayList;

public class Jouer extends AppCompatActivity {
    private TextView txtPseudo;
    private TextView txtScore;
    private TextView txtQuestion;
    private TextView txtValid;
    private RadioGroup reponses;
    private RadioButton r1;
    private RadioButton r2;
    private RadioButton r3;
    private RadioButton r4;
    private Button valider;
    private Controle controle;
    private ArrayList<Question> lesQuestions;
    private ArrayList<Reponse> lesReponses;
    private ArrayList<Reponse> reponsesQ = new ArrayList<Reponse>();
    private Integer i = 0;
    private Integer score;
    private String lePseudo;
    private Joueur j;
    private Partie p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jouer);
        init();
    }

    protected void init(){
        this.controle = Controle.getInstance(this);
        Bundle extras = getIntent().getExtras();
        lePseudo = extras.getString("pseudo");
        //Création de la partie pour calcul du score
        j = new Joueur(lePseudo);
        p = new Partie(j);

        txtPseudo = (TextView)findViewById(R.id.pseudo);
        txtScore = (TextView)findViewById(R.id.score);
        txtQuestion = (TextView)findViewById(R.id.question);
        reponses = (RadioGroup)findViewById(R.id.reponses);
        r1 = (RadioButton)findViewById(R.id.r1);
        r2 = (RadioButton)findViewById(R.id.r2);
        r3 = (RadioButton)findViewById(R.id.r3);
        r4 = (RadioButton)findViewById(R.id.r4);
        r1.setChecked(true);
        valider = (Button)findViewById(R.id.valider);
        txtValid = (TextView)findViewById(R.id.txtVerif);
        //Set les champs par défault pseudo et score
        txtPseudo.setText(lePseudo);
        txtScore.setText("0 Points");
        //Récupération des questions et des réponses
        lesQuestions = controle.getLesQuestions();
        lesReponses = controle.getLesReponses();
        //Affichage de la question i
        txtQuestion.setText(i+1+"°/ "+lesQuestions.get(i).getLibelle());
        //Récupération des réponses de la question i
        recupReponses(lesQuestions.get(i));
        //Appel de la fonction pour le click du bouton Valider
        ecouteValid();
    }

    /**
     * Ecoute de l'événement sur le bouton Valider
     */
    public void ecouteValid(){
        valider.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Récupérer l'élément check
                RadioButton valide = (RadioButton)findViewById(reponses.getCheckedRadioButtonId());
                String val1 = valide.getText().toString();
                //Remettre par default le premier selectionné pour la prochaine question
                r1.setChecked(true);
                //Vérifier si la réponse était bonne ou non
                verifReponses(val1);
                // Si on arrive à plus de 10 questions, c'est la fin du Quizz sinon on continue le défilement des questions
                if(i < 9){
                    //On continue le quizz
                    i = i + 1;
                    txtQuestion.setText(i+1+"°/ "+lesQuestions.get(i).getLibelle());
                    recupReponses(lesQuestions.get(i));
                } else {
                    //Lancement d'une nouvelle activity pour la fin du Quizz
                    Intent suite = new Intent(getApplicationContext(), FinQuizz.class);
                    suite.putExtra("pseudo", lePseudo);
                    suite.putExtra("score", score);
                    startActivity(suite);
                }
            }
        });
    }

    /**
     * Récupération des réponses de la question i
     * @param q
     */
    public void recupReponses(Question q){
        //Nettoyer l'arraylist
        reponsesQ.clear();
        //Pour toutes les réponses, on veut uniquement celle de la question i et on l'ajoute dans une nouvelle arraylist
        for(Reponse r : lesReponses){
            if(r.getQu_id() == q.getQu_id()){
                reponsesQ.add(r);
            }
        }
        //Set les réponses dans les boutons radio
        r1.setText(reponsesQ.get(0).getReponse().toString());
        r2.setText(reponsesQ.get(1).getReponse().toString());
        r3.setText(reponsesQ.get(2).getReponse().toString());
        r4.setText(reponsesQ.get(3).getReponse().toString());
    }

    /**
     * Vérification de la réponse si elle est bonne ou non + Calcul du score via la classe Partie et la méthode calculScore
     * @param r
     */
    public void verifReponses(String r){
        String BonneReponse = null;
        String phrase;
        //Pour toutes les réponses, on cherche celle qui est bonne
        for(Reponse re : reponsesQ){
            if(re.getValid() == 1){
                BonneReponse = re.getReponse().toString();
            }
        }
        //Si le button radio selectionné est celle de la bonne réponse alors on lance le calcul du score sinon on fait rien
        if(r.equals(BonneReponse)){
            score = p.calculScore();
            phrase = "Bravo, vous avez eu la bonne réponse !";
            txtValid.setTextColor(Color.GREEN);
            txtScore.setText(score + " Points");
        } else {
            phrase = "Tu n'as pas eu la bonne réponse ! La bonne réponse était " + BonneReponse + ".";
            txtValid.setTextColor(Color.RED);
        }
        txtValid.setText(phrase);
    }
}
