package fr.ecole.eni.lokacar.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

<<<<<<< Updated upstream
import fr.ecole.eni.lokacar.helper.Helper;
=======
import java.util.ArrayList;
import java.util.List;

import fr.ecole.eni.lokacar.helper.GerantHelper;
>>>>>>> Stashed changes
import fr.ecole.eni.lokacar.modeles.Gerant;

public class GerantDAL {

<<<<<<< Updated upstream
    private Helper dbGerantHelper;
=======
    private GerantHelper dbGerantHelper;
    private Context mContext;
>>>>>>> Stashed changes

    public GerantDAL(Context context){
        this.dbGerantHelper = new Helper(context);
    }

    /**
     * Create ContentValues with gerant
     * @param gerant
     * @return
     */
    private ContentValues constructValuesDB(Gerant gerant) {
        ContentValues values = new ContentValues();
        values.put(GerantContract.GERANT_ID, gerant.getIdGerant());
        values.put(GerantContract.LOGIN, gerant.getLogin());
        values.put(GerantContract.PASSWORD,gerant.getMotDePasse());
        values.put(GerantContract.AGENCE,gerant.getAgence());
        return values;
    }

    public long insertGerant(Gerant gerant){
        SQLiteDatabase db = dbGerantHelper.getWritableDatabase();

        long id = db.insert(GerantContract.TABLE_GERANT_NAME, null, constructValuesDB(gerant));

        db.close();

        return id;
    }

    public long insertOrUpdate(Gerant gerant){
        SQLiteDatabase db = dbGerantHelper.getWritableDatabase();
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
        SQLiteDatabase db = dbGerantHelper.getWritableDatabase();
        db.update(GerantContract.TABLE_GERANT_NAME, constructValuesDB(gerant),
                "IDGERANT="+id,
                null);
        db.close();
    }

    public List<Gerant> getListeByLoginPassword(String login, String password) {
        SQLiteDatabase db = dbGerantHelper.getReadableDatabase();
        Cursor cursor = db.query(GerantContract.TABLE_GERANT_NAME,
                null,
                GerantContract.LOGIN+"=? AND " + GerantContract.PASSWORD+"=?",
                new String[]{login, password},
                null,
                null,
                null);

        List<Gerant> objects = new ArrayList<Gerant>();

        if (cursor != null && cursor.moveToFirst()){
            do {
                long id = cursor.getLong(cursor.getColumnIndex(GerantContract.GERANT_ID));
                String loginGerant = cursor.getString(cursor.getColumnIndex(GerantContract.LOGIN));
                String passwordGerant = cursor.getString(cursor.getColumnIndex(GerantContract.PASSWORD));
                String agenceGerant = cursor.getString(cursor.getColumnIndex(GerantContract.AGENCE));
            } while (cursor.moveToNext());

            cursor.close();
        }

        return objects;
    }


}
