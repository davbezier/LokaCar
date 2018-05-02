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

    private List<Voiture> data;
    private CustomItemClickListener listener;


    public VoitureAdapter(List<Voiture> data, CustomItemClickListener listener) {
        this.data = data;
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

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Voiture voiture = data.get(position);
        holder.marqueVoiture.setText(String.valueOf(voiture.getMarque()));
        holder.modeleVoiture.setText(String.valueOf(voiture.getModele()));
        holder.typeVoiture.setText(String.valueOf(voiture.getType()));
        holder.immatriculationVoiture.setText(String.valueOf(voiture.getImmatriculation()));
        holder.nbPlaceVoiture.setText(String.valueOf(voiture.getNbPlaces()));
        holder.nbPorteVoiture.setText(String.valueOf(voiture.getNbPortes()));
        holder.motorisationVoiture.setText(String.valueOf(voiture.getMotorisation()));
        holder.climatisationVoiture.setText(String.valueOf(voiture.isClimatisation()));
        holder.isManuel.setText(String.valueOf(voiture.isManuel()));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView marqueVoiture;
        TextView modeleVoiture;
        TextView typeVoiture;
        TextView immatriculationVoiture;
        TextView nbPlaceVoiture;
        TextView nbPorteVoiture;
        TextView motorisationVoiture;
        TextView climatisationVoiture;
        TextView isManuel;

        public ViewHolder(View itemView) {
            super(itemView);

            marqueVoiture = itemView.findViewById(R.id.marqueVoiture);
            modeleVoiture = itemView.findViewById(R.id.modeleVoiture);
            typeVoiture = itemView.findViewById(R.id.typeVoiture);
            immatriculationVoiture = itemView.findViewById(R.id.immatriculationVoiture);
            nbPlaceVoiture = itemView.findViewById(R.id.nbPlaceVoiture);
            nbPorteVoiture = itemView.findViewById(R.id.nbPorteVoiture);
            motorisationVoiture = itemView.findViewById(R.id.motorisationVoiture);
            climatisationVoiture = itemView.findViewById(R.id.climatisationVoiture);
            isManuel = itemView.findViewById(R.id.isManuel);

        }
    }


    public interface CustomItemClickListener{
        public void onItemClick(View v, int position);
    }
}
