package fr.ecole.eni.lokacar.modeles;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Location implements Parcelable {

    private long idLocation;
    private String dateDebut;
    private String dateFin;
    private double prixTotal;
    private String photoDebut;
    private String photoFin;
    private Client client;
    private Voiture voiture;
    private boolean isRendu;

    public Location(String dateDebut, String dateFin, double prixTotal, String photoDebut, String photoFin, Client client, Voiture voiture, boolean isRendu) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prixTotal = prixTotal;
        this.photoDebut = photoDebut;
        this.photoFin = photoFin;
        this.client = client;
        this.voiture = voiture;
        this.isRendu = isRendu;
    }

    protected Location(Parcel in) {
        idLocation = in.readLong();
        dateDebut = in.readString();
        dateFin = in.readString();
        prixTotal = in.readDouble();
        photoDebut = in.readString();
        photoFin = in.readString();
        client = in.readParcelable(Client.class.getClassLoader());
        voiture = in.readParcelable(Voiture.class.getClassLoader());
        isRendu = in.readByte() != 0;
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    public long getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(long idLocation) {
        this.idLocation = idLocation;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public String getPhotoDebut() {
        return photoDebut;
    }

    public void setPhotoDebut(String photoDebut) {
        this.photoDebut = photoDebut;
    }

    public String getPhotoFin() {
        return photoFin;
    }

    public void setPhotoFin(String photoFin) {
        this.photoFin = photoFin;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    public boolean isRendu() {
        return isRendu;
    }

    public void setRendu(boolean rendu) {
        isRendu = rendu;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idLocation);
        dest.writeString(dateDebut);
        dest.writeString(dateFin);
        dest.writeDouble(prixTotal);
        dest.writeString(photoDebut);
        dest.writeString(photoFin);
        dest.writeParcelable(client, flags);
        dest.writeParcelable(voiture, flags);
        dest.writeByte((byte) (isRendu ? 1 : 0));
    }

    @Override
    public String toString() {
        return "Location{" +
                "idLocation=" + idLocation +
                ", dateDebut='" + dateDebut + '\'' +
                ", dateFin='" + dateFin + '\'' +
                ", prixTotal=" + prixTotal +
                ", photoDebut='" + photoDebut + '\'' +
                ", photoFin='" + photoFin + '\'' +
                ", client=" + client +
                ", voiture=" + voiture +
                ", isRendu=" + isRendu +
                '}';
    }
}
