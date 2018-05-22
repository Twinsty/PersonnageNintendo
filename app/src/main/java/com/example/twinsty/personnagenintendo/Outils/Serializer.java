package com.example.twinsty.personnagenintendo.Outils;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Twinsty on 06/05/2018.
 */

public abstract class Serializer {

    /**
     * Sérialiser une donnée
     * @param filename
     * @param object
     * @param context
     */
    public static void serialise(String filename, Object object, Context context){
        try {
            FileOutputStream file = context.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream ocs;
            try {
                ocs = new ObjectOutputStream(file);
                ocs.writeObject(object);
                ocs.flush();
                ocs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Désarialiser des données
     * @param filename
     * @param context
     * @return
     */
    public static Object deSeralise(String filename, Context context){
        try {
            FileInputStream file = context.openFileInput(filename);
            ObjectInputStream ois;
            try {
                ois = new ObjectInputStream(file);
                try {
                    Object object = ois.readObject();
                    ois.close();
                    return object;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
