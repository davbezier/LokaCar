package fr.ecole.eni.lokacar.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import fr.ecole.eni.lokacar.dal.GerantContract;
import fr.ecole.eni.lokacar.dal.VoitureContract;

public class VoitureHelper extends SQLiteOpenHelper {

    public VoitureHelper(Context context) {
        super(context, VoitureContract.DATABASE_NAME, null, GerantContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(VoitureContract.VOITURE_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(VoitureContract.QUERY_DELETE_TABLE_VOITURES);
        onCreate(db);
    }
}
