package fr.ecole.eni.lokacar.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import fr.ecole.eni.lokacar.dal.GerantContract;

public class GerantHelper extends SQLiteOpenHelper {


    public GerantHelper(Context context) {
        super(context, GerantContract.DATABASE_NAME, null, GerantContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(GerantContract.GERANT_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(GerantContract.QUERY_DELETE_TABLE_GERANT);
        onCreate(db);
    }
}
