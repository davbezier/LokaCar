package fr.ecole.eni.lokacar.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import fr.ecole.eni.lokacar.dal.ClientContract;

public class ClientHelper  extends SQLiteOpenHelper{


    public ClientHelper(Context context) {
        super(context, ClientContract.DATABASE_NAME, null, ClientContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ClientContract.CLIENT_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(ClientContract.QUERY_DELETE_TABLE_CLIENT);
        onCreate(db);

    }
}
