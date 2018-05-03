package fr.ecole.eni.lokacar.adapter;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import fr.ecole.eni.lokacar.AccueilActivity;
import fr.ecole.eni.lokacar.R;
import fr.ecole.eni.lokacar.dal.LocationDAL;
import fr.ecole.eni.lokacar.dal.VoitureDAL;
import fr.ecole.eni.lokacar.modeles.Location;

import org.w3c.dom.Text;

import java.util.List;

public class VoitureLoueeAdapter extends RecyclerView.Adapter<VoitureLoueeAdapter.ViewHolder>{

    private List<Location> locationsVoituresLouees;
    private Context context;


    public VoitureLoueeAdapter(List<Location> locationsVoituresLouees) {
        this.locationsVoituresLouees = locationsVoituresLouees;

    }

    @NonNull
    @Override
    public VoitureLoueeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.voitureloueesrecycler, parent,false);

        final ViewHolder holder = new ViewHolder(view);
        context = parent.getContext();

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull VoitureLoueeAdapter.ViewHolder holder, int position) {


        final Location location = locationsVoituresLouees.get(position);
        holder.marque.setText(String.valueOf(location.getVoiture().getMarque()));
        holder.modele.setText(String.valueOf(location.getVoiture().getModele()));
        holder.type.setText(String.valueOf(location.getVoiture().getType()));
        holder.immatriculation.setText(String.valueOf(location.getVoiture().getImmatriculation()));
        holder.nbPlace.setText(String.valueOf(location.getVoiture().getNbPlaces()));
        holder.nbPortes.setText(String.valueOf(location.getVoiture().getNbPortes()));
        holder.motorisation.setText(String.valueOf(location.getVoiture().getMotorisation()));
        if (location.getVoiture().isClimatisation()){
            holder.climatisation.setText(String.valueOf("Non"));
        } else{
            holder.climatisation.setText(String.valueOf("Oui"));
        }

        if(location.getVoiture().isManuel()){
            holder.isManuel.setText(String.valueOf("Automatique"));
        }else{
            holder.isManuel.setText(String.valueOf("Manuelle"));
        }

        holder.prenomClient.setText(String.valueOf(location.getClient().getPrenom()));
        holder.nomClient.setText(String.valueOf(location.getClient().getNom()));
        holder.dateDebut.setText(String.valueOf(location.getDateDebut()));
        holder.dateFin.setText(String.valueOf(location.getDateFin()));
        holder.btnRendreVoiture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //update la location en placant voiturerendue=true;
                LocationDAL locationDAL = new LocationDAL(context);
                location.setRendu(true);
                locationDAL.update(location.getIdLocation(), location);
                Intent intent = new Intent(context, AccueilActivity.class);
                v.getContext().startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return locationsVoituresLouees.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView marque;
        TextView modele;
        TextView type;
        TextView immatriculation;
        TextView nbPlace;
        TextView nbPortes;
        TextView motorisation;
        TextView climatisation;
        TextView isManuel;
        TextView prenomClient;
        TextView nomClient;
        TextView dateDebut;
        TextView dateFin;
        Button btnRendreVoiture;


        public ViewHolder(View itemView) {
            super(itemView);
            marque = itemView.findViewById(R.id.marqueVoiturelouee);
            modele = itemView.findViewById(R.id.modeleVoiturelouee);
            type = itemView.findViewById(R.id.typeVoiturelouee);
            immatriculation = itemView.findViewById(R.id.immatriculationVoiturelouee);
            nbPlace = itemView.findViewById(R.id.nbPlaceVoiturelouee);
            nbPortes = itemView.findViewById(R.id.nbPorteVoiturelouee);
            motorisation = itemView.findViewById(R.id.motorisationVoiturelouee);
            climatisation = itemView.findViewById(R.id.climatisationVoiturelouee);
            isManuel = itemView.findViewById(R.id.isManuellouee);
            prenomClient = itemView.findViewById(R.id.prenomClientLouee);
            nomClient = itemView.findViewById(R.id.nomClientLouee);
            dateDebut = itemView.findViewById(R.id.dateDebutVoitureLouee);
            dateFin = itemView.findViewById(R.id.dateFinVoitureLouee);
            btnRendreVoiture = itemView.findViewById(R.id.btnRendreVoiture);


        }
    }


}
