package fr.ecole.eni.lokacar.dal;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fr.ecole.eni.lokacar.AccueilActivity;
import fr.ecole.eni.lokacar.AfficherVoituresDisponiblesActivity;
import fr.ecole.eni.lokacar.helper.Helper;

import fr.ecole.eni.lokacar.modeles.Location;
import fr.ecole.eni.lokacar.modeles.Voiture;

public class VoitureDAL {

    private Helper dbVoitureHelper;
    private Context context;

    public VoitureDAL(Context context){
        dbVoitureHelper = new Helper(context);
        this.context = context;
    }

    private ContentValues constructValuesDB(Voiture voiture){
        ContentValues values = new ContentValues();
        //values.put(VoitureContract.VOITURE_ID, voiture.getIdVoiture());
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

    public List<Voiture> getAllVoiture(){

        SQLiteDatabase db = dbVoitureHelper.getReadableDatabase();

        Cursor cursor = db.query(VoitureContract.TABLE_VOITURES_NAME,
                null,
                null,
                null,
                null,
                null,
                VoitureContract.MARQUE);

        List<Voiture> objects = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()){
            do {
                long id = cursor.getLong(cursor.getColumnIndex(VoitureContract.VOITURE_ID));
                String marqueVoiture = cursor.getString(cursor.getColumnIndex(VoitureContract.MARQUE));
                String modeleVoiture = cursor.getString(cursor.getColumnIndex(VoitureContract.MODELE));
                String typeVoiture = cursor.getString(cursor.getColumnIndex(VoitureContract.TYPE));
                String immatriculationVoiture = cursor.getString(cursor.getColumnIndex(VoitureContract.IMMATRICULATION));
                int nbPlaceVoiture = cursor.getInt(cursor.getColumnIndex(VoitureContract.NBPLACES));
                int nbPorteVoiture = cursor.getInt(cursor.getColumnIndex(VoitureContract.NBPORTES));
                String motorisationVoiture = cursor.getString(cursor.getColumnIndex(VoitureContract.MOTORISATION));
                boolean climatisationVoiture = (cursor.getInt(cursor.getColumnIndex(VoitureContract.CLIMATISATION))>0);
                boolean isManuel = (cursor.getInt(cursor.getColumnIndex(VoitureContract.ISMANUEL))>0);

                objects.add(new Voiture(id, marqueVoiture,
                        modeleVoiture, typeVoiture,
                        immatriculationVoiture, nbPlaceVoiture,
                        nbPorteVoiture, motorisationVoiture,
                        climatisationVoiture, isManuel));
            } while (cursor.moveToNext());

            cursor.close();
        }

        return objects;
    }

    public List<Voiture> getAllvoituresNonLouees(){

        List<Voiture> allVoituresNonLouees = new ArrayList<>();

        List<Voiture> allVoitures = this.getAllVoiture();

        LocationDAL locationDAL = new LocationDAL(context);

        List<Location> allLocationsVoituresLouees = locationDAL.getAllLocationsWithVoituresNonRendues();

        for (Voiture voiture : allVoitures){

           for(Location location : allLocationsVoituresLouees){

               if (voiture.getIdVoiture() != location.getVoiture().getIdVoiture()){
                   allVoituresNonLouees.add(voiture);
               }
           }
        }

        return  allVoituresNonLouees;
    }


}
