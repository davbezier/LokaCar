package fr.ecole.eni.lokacar.dal;

public abstract class VoitureContract {

    public final static String DATABASE_NAME = "LokaCar.db";
    public final static int DATABASE_VERSION = 1;

    public final static String VOITURE_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + "VOITURES ("
            + "IDVOITURE INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "MARQUE TEXT, "
            + "MODELE TEXT, "
            + "TYPE TEXT, "
            + "IMMATRICULATION TEXT, "
            + "NBPLACES INTEGER, "
            + "NBPORTES INTEGER, "
            + "MOTORISATION TEXT, "
            + "CLIMATISATION INTEGER, "
            + "ISMANUEL INTEGER)";

    public final static String QUERY_DELETE_TABLE_VOITURES = "DROP TABLE IF EXISTS VOITURES";
    public final static String TABLE_VOITURES_NAME = "VOITURES";

    public final static String VOITURE_ID = "IDVOITURE";
    public final static String MARQUE = "MARQUE";
    public final static String MODELE = "MODELE";
    public final static String TYPE = "TYPE";
    public final static String IMMATRICULATION = "IMMATRICULATION";
    public final static String NBPLACES = "NBPLACES";
    public final static String NBPORTES = "NBPORTES";
    public final static String MOTORISATION = "MOTORISATION";
    public final static String CLIMATISATION = "CLIMATISATION";
    public final static String ISMANUEL = "ISMANUEL";

}
