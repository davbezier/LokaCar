package fr.ecole.eni.lokacar.modeles;

import android.os.Parcel;
import android.os.Parcelable;

public class Gerant implements Parcelable{

    private long idGerant;
    private String login;
    private String motDePasse;
    private String agence;

    public Gerant(long idGerant, String login, String motDePasse, String agence) {
        this.idGerant = idGerant;
        this.login = login;
        this.motDePasse = motDePasse;
        this.agence = agence;
    }

    public Gerant(String login, String motDePasse, String agence) {
        this.login = login;
        this.motDePasse = motDePasse;
        this.agence = agence;
    }

    protected Gerant(Parcel in) {
        idGerant = in.readLong();
        login = in.readString();
        motDePasse = in.readString();
        agence = in.readString();
    }

    public static final Creator<Gerant> CREATOR = new Creator<Gerant>() {
        @Override
        public Gerant createFromParcel(Parcel in) {
            return new Gerant(in);
        }

        @Override
        public Gerant[] newArray(int size) {
            return new Gerant[size];
        }
    };

    public long getIdGerant() {
        return idGerant;
    }

    public void setIdGerant(long idGerant) {
        this.idGerant = idGerant;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getAgence() {
        return agence;
    }

    public void setAgence(String agence) {
        this.agence = agence;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idGerant);
        dest.writeString(login);
        dest.writeString(motDePasse);
        dest.writeString(agence);
    }

    @Override
    public String toString() {
        return "Gerant{" +
                "idGerant=" + idGerant +
                ", login='" + login + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", agence='" + agence + '\'' +
                '}';
    }
}
