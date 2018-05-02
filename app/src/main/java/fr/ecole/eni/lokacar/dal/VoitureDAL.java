package fr.ecole.eni.lokacar.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import fr.ecole.eni.lokacar.helper.Helper;

import fr.ecole.eni.lokacar.modeles.Voiture;

public class VoitureDAL {

    private Helper dbVoitureHelper;

    public VoitureDAL(Context context){
        dbVoitureHelper = new Helper(context);
    }

    private ContentValues constructValuesDB(Voiture voiture){
        ContentValues values = new ContentValues();
        values.put(VoitureContract.VOITURE_ID, voiture.getIdVoiture());
        values.put(VoitureContract.MARQUE, voiture.getMarque());
        values.put(VoitureContract.MODELE, voiture.getModele());
        values.put(VoitureContract.TYPE, voiture.getType());
        values.put(VoitureContract.IMMATRICULATION, voiture.getImmatriculation());
        values.put(VoitureContract.NBPLACES, voiture.getNbPlaces());
        values.put(VoitureContract.NBPORTES, voiture.getNbPortes());
        values.put(VoitureContract.MOTORISATION, voiture.getMotorisation());
        values.put(VoitureContract.CLIMATISATION, voiture.isClimatisation()? 1 : 0);
        values.put(VoitureContract.ISMANUEL, voiture.isManuel()? 1 : 0);

        return values;
    }


    public long insertVoiture(Voiture voiture){
        SQLiteDatabase db = dbVoitureHelper.getWritableDatabase();

        long id = db.insert(VoitureContract.TABLE_VOITURES_NAME, null, constructValuesDB(voiture));
        db.close();
        return id;
    }

    public void update (long id, Voiture voiture){
        SQLiteDatabase db = dbVoitureHelper.getWritableDatabase();
        db.update(VoitureContract.TABLE_VOITURES_NAME, constructValuesDB(voiture), VoitureContract.VOITURE_ID+""+id, null);
        db.close();
    }

}
