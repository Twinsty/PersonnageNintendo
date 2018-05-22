package com.example.twinsty.personnagenintendo.Vue;

import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.twinsty.personnagenintendo.R;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class DetailsPerso extends AppCompatActivity {
    /**
     * Attributs
     */
    private TextView peNom;
    private TextView peOrigine;
    private TextView peSexe;
    private TextView peEspece;
    private TextView pePhysique;
    private TextView peArme;
    private TextView pePouvoirs;
    private TextView peEnnemi;
    private ImageView image;
    private MediaPlayer mPlayer = null;

    /**
     * Toutes les photos dans un tableau
     */
    int[] lesPhotos = {
            R.drawable.mario,
            R.drawable.link2,
            R.drawable.wario,
            R.drawable.toad,
            R.drawable.yoshi,
            R.drawable.pit,
            R.drawable.donkeykong,
            R.drawable.kirby,
            R.drawable.diddykong,
            R.drawable.olimar,
            R.drawable.sacha,
            R.drawable.pikachu,
            R.drawable.zelda,
            R.drawable.peach,
            R.drawable.bowser,
            R.drawable.luigi
    };

    int[] lesSons = {
            R.raw.mario,
            R.raw.link,
            R.raw.wario,
            R.raw.toad,
            R.raw.yoshi,
            R.raw.pit,
            R.raw.donkeykong,
            R.raw.kirby,
            R.raw.diddykong,
            R.raw.olimar,
            1,
            R.raw.pikachu,
            R.raw.zelda,
            R.raw.peach,
            R.raw.bowser,
            R.raw.luigi
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_perso);
        init_affiche();
    }

    /**
     * Initialisation et affichage dans la vue
     */
    private void init_affiche(){
        Bundle extras = getIntent().getExtras();
        //Récupération de tout les champs
        String leNom = extras.getString("nom");
        String lOrigine = extras.getString("origine");
        String leSexe = extras.getString("sexe");
        leSexe = recupSexe(leSexe);
        String lEspece = extras.getString("espece");
        String lePhysique = extras.getString("physique");
        String lArme = extras.getString("arme");
        String lePouvoirs = extras.getString("pouvoirs");
        String lEnnemi = extras.getString("ennemi");
        final Integer i = extras.getInt("id");
        //Initialisation des liens entre les objets graphiques
        peNom = (TextView) findViewById(R.id.nom);
        peOrigine = (TextView)findViewById(R.id.origine);
        peSexe = (TextView)findViewById(R.id.sexe);
        peEspece = (TextView)findViewById(R.id.espece);
        pePhysique = (TextView)findViewById(R.id.physique);
        peArme = (TextView)findViewById(R.id.arme);
        pePouvoirs = (TextView)findViewById(R.id.pouvoirs);
        peEnnemi = (TextView)findViewById(R.id.ennemi);
        image = (ImageView) findViewById(R.id.imv);
        //Set tous les champs
        peNom.setText(leNom);
        peOrigine.setText(lOrigine);
        peSexe.setText(leSexe);
        peEspece.setText(lEspece);
        pePhysique.setText(lePhysique);
        peArme.setText(lArme);
        pePouvoirs.setText(lePouvoirs);
        peEnnemi.setText(lEnnemi);
        int nb=lesPhotos.length;
        Log.i("nombre de Photos"," : "+nb);
        image.setImageResource(lesPhotos[i]);
        image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                playSound(lesSons[i]);
            }

        });
    }

    /**
     * Permet de changer le M ou le F du sexe du personnage contre le réel nom (Masculin ou Féminin)
     * @param peSexe
     * @return
     */
    private String recupSexe(String peSexe){
        if(peSexe.equals("M")){
            peSexe = "Masculin";
        } else if(peSexe.equals("/")){
            peSexe = "Masculin / Féminin";
        } else {
            peSexe = "Féminin";
        }
        return peSexe;
    }

    private void playSound(int resId) {
        if(mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
        }
        if(resId != 1) {
            mPlayer = MediaPlayer.create(this, resId);
            mPlayer.start();
        }
    }
}
