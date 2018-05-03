package fr.ecole.eni.lokacar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import fr.ecole.eni.lokacar.adapter.VoitureLoueeAdapter;
import fr.ecole.eni.lokacar.dal.LocationDAL;
import fr.ecole.eni.lokacar.modeles.Location;

public class AfficherVoituresLoueesActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Location> locationsEnCours;
    private LocationDAL locationDAL;
    private VoitureLoueeAdapter mVoitureLoueeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_voitures_louees);

        // on va récupérer la liste des locations en cours en BDD

        locationDAL = new LocationDAL(AfficherVoituresLoueesActivity.this);
        locationsEnCours = locationDAL.getAllLocationsWithVoituresNonRendues();


        mRecyclerView = findViewById(R.id.liste_locations_encours);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AfficherVoituresLoueesActivity.this);

        mRecyclerView.setLayoutManager(linearLayoutManager);

        mVoitureLoueeAdapter = new VoitureLoueeAdapter(locationsEnCours, new VoitureLoueeAdapter.CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

                Location location = locationsEnCours.get(position);

                //update la location en voitures rendues

            }
        });
        mRecyclerView.setAdapter(mVoitureLoueeAdapter);

    }
}
