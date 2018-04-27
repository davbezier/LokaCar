package fr.ecole.eni.lokacar.modeles;

import android.os.Parcel;
import android.os.Parcelable;

public class Voiture implements Parcelable{

    private long idVoiture;
    private String marque;
    private String modele;
    private String type;
    private String immatriculation;
    private int nbPlaces;
    private int nbPortes;
    private String motorisation;
    private boolean climatisation;
    private boolean isManuel;

    public Voiture(long idVoiture, String marque, String modele, String type, String immatriculation, int nbPlaces, int nbPortes, String motorisation, boolean climatisation, boolean isManuel) {
        this.idVoiture = idVoiture;
        this.marque = marque;
        this.modele = modele;
        this.type = type;
        this.immatriculation = immatriculation;
        this.nbPlaces = nbPlaces;
        this.nbPortes = nbPortes;
        this.motorisation = motorisation;
        this.climatisation = climatisation;
        this.isManuel = isManuel;
    }

    public Voiture(String marque, String modele, String type, String immatriculation, int nbPlaces, int nbPortes, String motorisation, boolean climatisation, boolean isManuel) {
        this.marque = marque;
        this.modele = modele;
        this.type = type;
        this.immatriculation = immatriculation;
        this.nbPlaces = nbPlaces;
        this.nbPortes = nbPortes;
        this.motorisation = motorisation;
        this.climatisation = climatisation;
        this.isManuel = isManuel;
    }

    protected Voiture(Parcel in) {
        idVoiture = in.readLong();
        marque = in.readString();
        modele = in.readString();
        type = in.readString();
        immatriculation = in.readString();
        nbPlaces = in.readInt();
        nbPortes = in.readInt();
        motorisation = in.readString();
        climatisation = in.readByte() != 0;
        isManuel = in.readByte() != 0;
    }

    public static final Creator<Voiture> CREATOR = new Creator<Voiture>() {
        @Override
        public Voiture createFromParcel(Parcel in) {
            return new Voiture(in);
        }

        @Override
        public Voiture[] newArray(int size) {
            return new Voiture[size];
        }
    };

    public long getIdVoiture() {
        return idVoiture;
    }

    public void setIdVoiture(long idVoiture) {
        this.idVoiture = idVoiture;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public int getNbPortes() {
        return nbPortes;
    }

    public void setNbPortes(int nbPortes) {
        this.nbPortes = nbPortes;
    }

    public String getMotorisation() {
        return motorisation;
    }

    public void setMotorisation(String motorisation) {
        this.motorisation = motorisation;
    }

    public boolean isClimatisation() {
        return climatisation;
    }

    public void setClimatisation(boolean climatisation) {
        this.climatisation = climatisation;
    }

    public boolean isManuel() {
        return isManuel;
    }

    public void setManuel(boolean manuel) {
        isManuel = manuel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idVoiture);
        dest.writeString(marque);
        dest.writeString(modele);
        dest.writeString(type);
        dest.writeString(immatriculation);
        dest.writeInt(nbPlaces);
        dest.writeInt(nbPortes);
        dest.writeString(motorisation);
        dest.writeByte((byte) (climatisation ? 1 : 0));
        dest.writeByte((byte) (isManuel ? 1 : 0));
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "idVoiture=" + idVoiture +
                ", marque='" + marque + '\'' +
                ", modele='" + modele + '\'' +
                ", type='" + type + '\'' +
                ", immatriculation='" + immatriculation + '\'' +
                ", nbPlaces=" + nbPlaces +
                ", nbPortes=" + nbPortes +
                ", motorisation='" + motorisation + '\'' +
                ", climatisation=" + climatisation +
                ", isManuel=" + isManuel +
                '}';
    }
}
