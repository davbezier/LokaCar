package fr.ecole.eni.lokacar.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fr.ecole.eni.lokacar.helper.Helper;
import fr.ecole.eni.lokacar.modeles.Client;
import fr.ecole.eni.lokacar.modeles.Location;
import fr.ecole.eni.lokacar.modeles.Voiture;

public class LocationDAL {

    private Helper helper;

    public LocationDAL(Context context) {
        helper = new Helper(context);
    }

    private ContentValues constructValuesDB(Location location) {
        ContentValues values = new ContentValues();
        values.put(LocationContract.DATE_DEBUT, location.getDateDebut());
        values.put(LocationContract.DATE_FIN, location.getDateFin());
        values.put(LocationContract.PRIX_TOTAL, location.getPrixTotal());
        values.put(LocationContract.PHOTO_DEBUT, location.getPhotoDebut());
        values.put(LocationContract.PHOTO_FIN, location.getPhotoFin());
        values.put(LocationContract.IDCLIENT, location.getClient().getIdClient());
        values.put(LocationContract.IDVVOITURE, location.getVoiture().getIdVoiture());
        values.put(LocationContract.ISRENDUE, location.isRendu() ? 1 : 0);
        return values;
    }

    public long insertLocation(Location location) {
        SQLiteDatabase db = helper.getWritableDatabase();

        long id = db.insert(LocationContract.TABLE_LOCATIONS_NAME, null, constructValuesDB(location));
        db.close();
        return id;
    }

    public void update(long id, Location location) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.update(LocationContract.TABLE_LOCATIONS_NAME, constructValuesDB(location), LocationContract.LOCATION_ID + "" + id, null);
        db.close();
    }



    public List<Location> getAllLocationsWithVoituresNonRendues() {
        SQLiteDatabase db = helper.getReadableDatabase();


        String whereClauseLoc = LocationContract.ISRENDUE + " = ?";
        String[] whereArgsLoc = new String[]{
                "0"};

        Cursor cursorLoc = db.query(LocationContract.TABLE_LOCATIONS_NAME, null, whereClauseLoc, whereArgsLoc, null, null, null);

        List<Location> listeLocationsVoituresNonRendues = new ArrayList<>();

        if (cursorLoc != null && cursorLoc.moveToFirst()) {
            do {
                long idLocation = cursorLoc.getLong(cursorLoc.getColumnIndex(LocationContract.LOCATION_ID));
                String dateDebut = cursorLoc.getString(cursorLoc.getColumnIndex(LocationContract.DATE_DEBUT));
                String dateFin = cursorLoc.getString(cursorLoc.getColumnIndex(LocationContract.DATE_FIN));
                double prixTotal = cursorLoc.getDouble(cursorLoc.getColumnIndex(LocationContract.PRIX_TOTAL));
                String photoDebut = cursorLoc.getString(cursorLoc.getColumnIndex(LocationContract.PHOTO_DEBUT));
                String photoFin = cursorLoc.getString(cursorLoc.getColumnIndex(LocationContract.PHOTO_FIN));

                long idClient = cursorLoc.getLong(cursorLoc.getColumnIndex(LocationContract.IDCLIENT));
                //on récupère en BDD le client

                String whereClauseCli = ClientContract.CLIENTS_ID + " = " + idClient;

                Cursor cursorCli = db.query(ClientContract.TABLE_CLIENT_NAME,
                        null,
                        whereClauseCli,
                        null,
                        null,
                        null,
                        null);
                Client client = null;
                if (cursorCli != null && cursorCli.moveToFirst()) {
                    do {
                        long id = cursorCli.getLong(cursorCli.getColumnIndex(ClientContract.CLIENTS_ID));
                        String nomClient = cursorCli.getString(cursorCli.getColumnIndex(ClientContract.NOM));
                        String prenomClient = cursorCli.getString(cursorCli.getColumnIndex(ClientContract.PRENOM));
                        String adresseClient = cursorCli.getString(cursorCli.getColumnIndex(ClientContract.ADRESSE));
                        String telephoneClient = cursorCli.getString(cursorCli.getColumnIndex(ClientContract.TELEPHONE));
                        String emailClient = cursorCli.getString(cursorCli.getColumnIndex(ClientContract.EMAIL));

                        client = new Client(id, nomClient, prenomClient, adresseClient, telephoneClient, emailClient);
                    } while (cursorCli.moveToNext());

                    cursorCli.close();
                }


                long idVoiture = cursorLoc.getLong(cursorLoc.getColumnIndex(LocationContract.IDVVOITURE));
                //on récupère en BDD la voiture

                String whereClauseVoit = VoitureContract.VOITURE_ID + " = " + idVoiture;
                Cursor cursorVoit = db.query(VoitureContract.TABLE_VOITURES_NAME,
                        null,
                        whereClauseVoit,
                        null,
                        null,
                        null,
                        VoitureContract.MARQUE);

                Voiture voiture = null;
                Boolean isRendue = false;
                if (cursorVoit != null && cursorVoit.moveToFirst()) {
                    do {
                        long id = cursorVoit.getLong(cursorVoit.getColumnIndex(VoitureContract.VOITURE_ID));
                        String marqueVoiture = cursorVoit.getString(cursorVoit.getColumnIndex(VoitureContract.MARQUE));
                        String modeleVoiture = cursorVoit.getString(cursorVoit.getColumnIndex(VoitureContract.MODELE));
                        String typeVoiture = cursorVoit.getString(cursorVoit.getColumnIndex(VoitureContract.TYPE));
                        String immatriculationVoiture = cursorVoit.getString(cursorVoit.getColumnIndex(VoitureContract.IMMATRICULATION));
                        int nbPlaceVoiture = cursorVoit.getInt(cursorVoit.getColumnIndex(VoitureContract.NBPLACES));
                        int nbPorteVoiture = cursorVoit.getInt(cursorVoit.getColumnIndex(VoitureContract.NBPORTES));
                        String motorisationVoiture = cursorVoit.getString(cursorVoit.getColumnIndex(VoitureContract.MOTORISATION));
                        boolean climatisationVoiture = (cursorVoit.getInt(cursorVoit.getColumnIndex(VoitureContract.CLIMATISATION)) > 1);
                        boolean isManuel = (cursorVoit.getInt(cursorVoit.getColumnIndex(VoitureContract.ISMANUEL)) > 1);

                        voiture = new Voiture(id, marqueVoiture,
                                modeleVoiture, typeVoiture,
                                immatriculationVoiture, nbPlaceVoiture,
                                nbPorteVoiture, motorisationVoiture,
                                climatisationVoiture, isManuel);
                    } while (cursorVoit.moveToNext());

                    cursorVoit.close();


                    long isRenduInt = cursorLoc.getLong(cursorLoc.getColumnIndex(LocationContract.ISRENDUE));

                    if (isRenduInt == 0) {
                        isRendue = false;
                    }
                    if (isRenduInt == 1) {
                        isRendue = true;
                    }

                }

                Location location = new Location(idLocation, dateDebut, dateFin, prixTotal, photoDebut, photoFin, client, voiture, isRendue);
                listeLocationsVoituresNonRendues.add(location);
            } while (cursorLoc.moveToNext());

            cursorLoc.close();

        }
        return listeLocationsVoituresNonRendues;
    }


}
