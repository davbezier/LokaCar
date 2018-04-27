package fr.ecole.eni.lokacar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AccueilActivity extends AppCompatActivity {

    private Button buttonEnregistrerVoitures;
    private Button buttonVoituresLouees;
    private Button buttonVoituresDisponibles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        buttonEnregistrerVoitures = findViewById(R.id.buttonEnregistrerVoiture);
        buttonVoituresDisponibles = findViewById(R.id.buttonVoituresDisponibles);
        buttonVoituresLouees = findViewById(R.id.buttonVoituresLouees);

        buttonEnregistrerVoitures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(AccueilActivity.this, EnregistrerVoitureActivity.class );
               startActivity(intent);
            }
        });

        buttonVoituresLouees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccueilActivity.this, AfficherVoituresLoueesActivity.class);
                startActivity(intent);
            }
        });

        buttonVoituresDisponibles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccueilActivity.this, AfficherVoituresDisponiblesActivity.class);
                startActivity(intent);
            }
        });
    }
}
