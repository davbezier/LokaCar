package fr.ecole.eni.lokacar.dal;

public abstract class ClientContract {

    public final static String DATABASE_NAME = "LokaCar.db";
    public final static int DATABASE_VERSION = 1;

    public final static String CLIENT_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + "CLIENTS ("
            + "IDCLIENT INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "NOM TEXT, "
            + "PRENOM TEXT, "
            + "ADRESSE TEXT, "
            + "TELEPHONE TEXT, "
            + "EMAIL TEXT)";

    public final static String QUERY_DELETE_TABLE_CLIENT = "DROP TABLE IF EXISTS CLIENTS";
    public final static String TABLE_CLIENT_NAME = "CLIENTS";

    public final static String CLIENTS_ID = "IDCLIENT";
    public final static String NOM = "NOM";
    public final static String PRENOM = "PRENOM";
    public final static String ADRESSE = "ADRESSE";
    public final static String TELEPHONE = "TELEPHONE";
    public final static String EMAIL = "EMAIL";

}
