package fr.ecole.eni.lokacar.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import fr.ecole.eni.lokacar.R;
import fr.ecole.eni.lokacar.modeles.Voiture;

public class VoitureAdapter extends RecyclerView.Adapter<VoitureAdapter.ViewHolder> {

    private List<Voiture> mVoitureList;
    private CustomItemClickListener listener;


    public VoitureAdapter(List<Voiture> mVoitureList, CustomItemClickListener listener) {
        this.mVoitureList = mVoitureList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VoitureAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);

       final ViewHolder holder = new ViewHolder(view);

       view.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (listener != null) {
                   listener.onItemClick(v, holder.getAdapterPosition());
               }
           }
       });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Voiture voiture = mVoitureList.get(position);
        holder.marque.setText(String.valueOf(voiture.getMarque()));
        holder.modele.setText(String.valueOf(voiture.getModele()));
        holder.type.setText(String.valueOf(voiture.getType()));
        holder.immatriculation.setText(String.valueOf(voiture.getImmatriculation()));
        holder.nbPlace.setText(String.valueOf(voiture.getNbPlaces()));
        holder.nbPorte.setText(String.valueOf(voiture.getNbPortes()));
        holder.motorisation.setText(String.valueOf(voiture.getMotorisation()));
        if (voiture.isClimatisation()){
            holder.climatisation.setText(String.valueOf("Oui"));
        } else {
            holder.climatisation.setText(String.valueOf("Non"));
        }

        if (voiture.isManuel()) {
            holder.isManuelV.setText(String.valueOf("Manuelle"));
        } else {
            holder.isManuelV.setText(String.valueOf("Automatique"));
        }


    }

    @Override
    public int getItemCount() {
        return mVoitureList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView marque;
        TextView modele;
        TextView type;
        TextView immatriculation;
        TextView nbPlace;
        TextView nbPorte;
        TextView motorisation;
        TextView climatisation;
        TextView isManuelV;

        public ViewHolder(View itemView) {
            super(itemView);

            marque = itemView.findViewById(R.id.marqueVoiture);
            modele = itemView.findViewById(R.id.modeleVoiture);
            type = itemView.findViewById(R.id.typeVoiture);
            immatriculation = itemView.findViewById(R.id.immatriculationVoiture);
            nbPlace = itemView.findViewById(R.id.nbPlaceVoiture);
            nbPorte = itemView.findViewById(R.id.nbPorteVoiture);
            motorisation = itemView.findViewById(R.id.motorisationVoiture);
            climatisation = itemView.findViewById(R.id.climatisationVoiture);
            isManuelV = itemView.findViewById(R.id.isManuel);

        }
    }


    public interface CustomItemClickListener{
        public void onItemClick(View v, int position);
    }
}
