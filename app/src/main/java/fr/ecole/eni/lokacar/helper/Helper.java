package fr.ecole.eni.lokacar.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import fr.ecole.eni.lokacar.dal.ClientContract;
import fr.ecole.eni.lokacar.dal.GerantContract;
import fr.ecole.eni.lokacar.dal.LocationContract;
import fr.ecole.eni.lokacar.dal.VoitureContract;

public class Helper extends SQLiteOpenHelper {


    public Helper(Context context) {
        super(context, GerantContract.DATABASE_NAME, null, GerantContract.DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(GerantContract.GERANT_CREATE_TABLE);
        db.execSQL(VoitureContract.VOITURE_CREATE_TABLE);
        db.execSQL(ClientContract.CLIENT_CREATE_TABLE);
        db.execSQL(LocationContract.LOCATION_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(GerantContract.QUERY_DELETE_TABLE_GERANT);
        db.execSQL(VoitureContract.QUERY_DELETE_TABLE_VOITURES);
        db.execSQL(ClientContract.QUERY_DELETE_TABLE_CLIENT);
        db.execSQL(LocationContract.QUERY_DELETE_TABLE_LOCATIONS);
        onCreate(db);
    }
}
