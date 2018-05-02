package fr.ecole.eni.lokacar;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import fr.ecole.eni.lokacar.adapter.VoitureAdapter;
import fr.ecole.eni.lokacar.dal.VoitureDAL;
import fr.ecole.eni.lokacar.modeles.Voiture;

public class AfficherVoituresDisponiblesActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Voiture> mVoitureList;
    private VoitureDAL mVoitureDAL;
    private VoitureAdapter mVoitureAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_voitures_disponibles);

        mRecyclerView = findViewById(R.id.liste_voitures_disponibles);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AfficherVoituresDisponiblesActivity.this);

        mRecyclerView.setLayoutManager(linearLayoutManager);

        mVoitureDAL = new VoitureDAL(AfficherVoituresDisponiblesActivity.this);

        mVoitureList = mVoitureDAL.getAllVoiture();

        mVoitureAdapter = new VoitureAdapter(mVoitureList, new VoitureAdapter.CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Voiture voiture = mVoitureList.get(position);

                Intent intent = new Intent(AfficherVoituresDisponiblesActivity.this, EnregistrementClientActivity.class);
                startActivityForResult(intent, position);
            }
        });

        mRecyclerView.setAdapter(mVoitureAdapter);

    }
}
