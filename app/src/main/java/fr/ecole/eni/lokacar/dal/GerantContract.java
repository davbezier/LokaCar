package fr.ecole.eni.lokacar.dal;

public abstract class GerantContract {

    public final static String DATABASE_NAME = "LokaCar.db";
    public final static int DATABASE_VERSION = 1;

    public final static String GERANT_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + "GERANTS ("
            + "IDGERANT INTEGER PRIMARY KEY, "
            + "LOGIN TEXT, "
            + "PASSWORD TEXT, "
            + "AGENCE TEXT)";

    public final static String QUERY_DELETE_TABLE_GERANT = "DROP TABLE IF EXISTS GERANTS";
    public final static String TABLE_GERANT_NAME = "GERANTS";

    public final static String GERANT_ID = "IDGERANT";
    public final static String LOGIN = "LOGIN";
    public final static String PASSWORD = "PASSWORD";
    public final static String AGENCE = "AGENCE";

}
