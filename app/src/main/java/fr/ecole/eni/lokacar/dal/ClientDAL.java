package fr.ecole.eni.lokacar.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fr.ecole.eni.lokacar.helper.Helper;
import fr.ecole.eni.lokacar.modeles.Client;

public class ClientDAL {

    private Helper dbClientHelper;

    public ClientDAL (Context context) {
        this.dbClientHelper = new Helper(context);
    }

    private ContentValues mContentValues (Client client) {
        ContentValues values = new ContentValues();
        values.put(ClientContract.NOM, client.getNom());
        values.put(ClientContract.PRENOM, client.getPrenom());
        values.put(ClientContract.ADRESSE, client.getAdresse());
        values.put(ClientContract.TELEPHONE, client.getTelephone());
        values.put(ClientContract.EMAIL, client.getEmail());
        return values;
    }

    public long insertClient(Client client){
        SQLiteDatabase db = dbClientHelper.getWritableDatabase();

        long id = db.insert(ClientContract.TABLE_CLIENT_NAME, null, mContentValues(client));

        db.close();

        return id;
    }

    public long insertOrUpdate(Client client) {
        SQLiteDatabase db = dbClientHelper.getWritableDatabase();

        long id = -1;

        Cursor c = db.query(ClientContract.TABLE_CLIENT_NAME, null,
                "IDCLIENT="+client.getIdClient(),null, null,
                null,null);

        if (c.getCount()>0){
            update(id, client);
        } else {
            insertClient(client);
        }

        db.close();
        return id;
    }

    public void update(long id, Client client){
        SQLiteDatabase db = dbClientHelper.getWritableDatabase();

        db.update(ClientContract.TABLE_CLIENT_NAME, mContentValues(client),
                "IDGERANT="+id,
                null);
        db.close();
    }

    public List<Client> getAllClient(){
        SQLiteDatabase db = dbClientHelper.getReadableDatabase();

        Cursor cursor = db.query(ClientContract.TABLE_CLIENT_NAME,
                null,
                null,
                null,
                null,
                null,
                null);

        List<Client> objects = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()){
            do {
                long id = cursor.getLong(cursor.getColumnIndex(ClientContract.CLIENTS_ID));
                String nomClient = cursor.getString(cursor.getColumnIndex(ClientContract.NOM));
                String prenomClient = cursor.getString(cursor.getColumnIndex(ClientContract.PRENOM));
                String adresseClient = cursor.getString(cursor.getColumnIndex(ClientContract.ADRESSE));
                String telephoneClient = cursor.getString(cursor.getColumnIndex(ClientContract.TELEPHONE));
                String emailClient = cursor.getString(cursor.getColumnIndex(ClientContract.EMAIL));

                objects.add(new Client(id, nomClient, prenomClient, adresseClient, telephoneClient, emailClient));
            } while (cursor.moveToNext());

            cursor.close();
        }

        return objects;
    }

}
