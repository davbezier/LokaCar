package fr.ecole.eni.lokacar.dal;

public abstract class LocationContract {

    public final static String DATABASE_NAME = "LokaCar.db";
    public final static int DATABASE_VERSION = 1;

    public final static String LOCATION_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + "LOCATIONS ("
            + "IDLOCATION INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "DATE_DEBUT TEXT, "
            + "DATE_FIN TEXT, "
            + "PRIX_TOTAL REAL, "
            + "PHOTO_DEBUT TEXT, "
            + "PHOTO_FIN TEXT, "
            + "FOREIGN KEY(IDCLIENT) REFERENCES CLIENTS(IDCLIENT), "
            + "FOREIGN KEY(IDVOITURE) REFERENCES VOITURES(IDVOITURE), "
            + "ISRENDUE INTEGER)";

    public final static String QUERY_DELETE_TABLE_LOCATIONS = "DROP TABLE IF EXISTS LOCATIONS";
    public final static String TABLE_LOCATIONS_NAME = "LOCATIONS";

    public final static String LOCATION_ID = "IDLOCATION";
    public final static String DATE_DEBUT = "DATE_DEBUT";
    public final static String DATE_FIN = "DATE_FIN";
    public final static String PRIX_TOTAL = "PRIX_TOTAL";
    public final static String PHOTO_DEBUT = "PHOTO_DEBUT";
    public final static String PHOTO_FIN = "PHOTO_FIN";
    public final static String IDCLIENT = "IDCLIENT";
    public final static String IDVVOITURE = "IDVOITURE";
    public final static String ISRENDUE = "ISRENDUE";


}
