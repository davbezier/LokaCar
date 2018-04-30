package fr.ecole.eni.lokacar.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fr.ecole.eni.lokacar.helper.ClientHelper;
import fr.ecole.eni.lokacar.modeles.Client;

public class ClientDAL {

    private ClientHelper dbClientHelper;

    public ClientDAL (Context context) {
        this.dbClientHelper = new ClientHelper(context);
    }

    private ContentValues mContentValues (Client client) {
        ContentValues values = new ContentValues();
        values.put(ClientContract.CLIENTS_ID, client.getIdClient());
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

}
