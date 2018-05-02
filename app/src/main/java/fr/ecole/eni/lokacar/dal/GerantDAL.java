package fr.ecole.eni.lokacar.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import fr.ecole.eni.lokacar.helper.Helper;

import java.util.ArrayList;
import java.util.List;



import fr.ecole.eni.lokacar.modeles.Gerant;

public class GerantDAL {

    private Helper helper;

    private Context mContext;


    public GerantDAL(Context context){
        this.helper = new Helper(context);
    }

    /**
     * Create ContentValues with gerant
     * @param gerant
     * @return
     */
    private ContentValues constructValuesDB(Gerant gerant) {
        ContentValues values = new ContentValues();
        values.put(GerantContract.LOGIN, gerant.getLogin());
        values.put(GerantContract.PASSWORD,gerant.getMotDePasse());
        values.put(GerantContract.AGENCE,gerant.getAgence());
        return values;
    }

    public long insertGerant(Gerant gerant){
        SQLiteDatabase db = helper.getWritableDatabase();

        long id = db.insert(GerantContract.TABLE_GERANT_NAME, null, constructValuesDB(gerant));

        db.close();

        return id;
    }

    public long insertOrUpdate(Gerant gerant){
        SQLiteDatabase db = helper.getWritableDatabase();
        long id = -1;
        Cursor c = db.query(GerantContract.TABLE_GERANT_NAME, null,
                "IDGERANT="+gerant.getIdGerant(),null,null,
                null, null);

        if (c.getCount()>0){
            update(id, gerant);
        } else {
            insertGerant(gerant);
        }
        db.close();
        return id;
    }

    public void update(long id, Gerant gerant){
        SQLiteDatabase db = helper.getWritableDatabase();
        db.update(GerantContract.TABLE_GERANT_NAME, constructValuesDB(gerant),
                "IDGERANT="+id,
                null);
        db.close();
    }


    public boolean validerConnexion(String login, String password){

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+GerantContract.TABLE_GERANT_NAME+" WHERE "+GerantContract.LOGIN+"='"+login
                +"' AND "+GerantContract.PASSWORD+"='"+password+"'",null);
        if (cursor.getCount()>0){
            db.close();
            return true;
        }
        db.close();
        return false;
    }


}
