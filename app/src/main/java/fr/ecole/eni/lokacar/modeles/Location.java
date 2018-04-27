package fr.ecole.eni.lokacar.modeles;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Location implements Parcelable{

    private long idLocation;
    private Date dateDebut;
    private Date dateFin;
    private double prixTotal;
    private String photoDebut;
    private String photoFin;
    private Client client;
    private Voiture voiture;

    public Location(long idLocation, Date dateDebut, Date dateFin, double prixTotal, String photoDebut, String photoFin, Client client, Voiture voiture) {
        this.idLocation = idLocation;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prixTotal = prixTotal;
        this.photoDebut = photoDebut;
        this.photoFin = photoFin;
        this.client = client;
        this.voiture = voiture;
    }

    public Location(Date dateDebut, Date dateFin, double prixTotal, String photoDebut, String photoFin, Client client, Voiture voiture) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prixTotal = prixTotal;
        this.photoDebut = photoDebut;
        this.photoFin = photoFin;
        this.client = client;
        this.voiture = voiture;
    }

    protected Location(Parcel in) {
        idLocation = in.readLong();
        prixTotal = in.readDouble();
        photoDebut = in.readString();
        photoFin = in.readString();
        client = in.readParcelable(Client.class.getClassLoader());
        voiture = in.readParcelable(Voiture.class.getClassLoader());
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

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(idLocation);
        dest.writeDouble(prixTotal);
        dest.writeString(photoDebut);
        dest.writeString(photoFin);
        dest.writeParcelable(client, flags);
        dest.writeParcelable(voiture, flags);
    }

    @Override
    public String toString() {
        return "Location{" +
                "idLocation=" + idLocation +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", prixTotal=" + prixTotal +
                ", photoDebut='" + photoDebut + '\'' +
                ", photoFin='" + photoFin + '\'' +
                ", client=" + client +
                ", voiture=" + voiture +
                '}';
    }
}
