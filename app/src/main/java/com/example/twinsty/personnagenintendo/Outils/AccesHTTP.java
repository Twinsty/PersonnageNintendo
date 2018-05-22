package com.example.twinsty.personnagenintendo.Outils;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by Twinsty on 05/05/2018.
 */

public class AccesHTTP extends AsyncTask<String, Integer, Long>{
    /**
     * Attributs
     */
    private ArrayList<NameValuePair> parametres;
    private String ret = null;
    public AsyncResponse delegate = null;

    /**
     * Constructeur
     */
    public AccesHTTP(){
        parametres = new ArrayList<NameValuePair>();
    }

    /**
     * Ajout d'un paramètre post
     * @param nom
     * @param valeur
     */
    public void addParam(String nom, String valeur){
        parametres.add(new BasicNameValuePair(nom, valeur));
    }

    @Override
    /**
     *  Connexion en tâche de fond dans un thread séparé
     */
    protected Long doInBackground(String... strings) {
        HttpClient cnxHttp = new DefaultHttpClient();
        HttpPost paramCnx = new HttpPost(strings[0]);

        try {
            //Encodage des paramètres
            paramCnx.setEntity(new UrlEncodedFormEntity(parametres));
            //Connexion des envoit de paramètres, attente de réponse
            HttpResponse reponse = cnxHttp.execute(paramCnx);
            //Transformation de la réponse
            ret = EntityUtils.toString(reponse.getEntity());

        } catch (UnsupportedEncodingException e) {
            Log.d("Erreur encodage", "***********" + e.toString());
        } catch (ClientProtocolException e) {
            Log.d("Erreur de protocol", "***********" + e.toString());
        } catch (IOException e) {
            Log.d("Erreur d'input d'ouput", "***********" + e.toString());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Long result){
        delegate.processFinish(ret.toString());
    }
}
