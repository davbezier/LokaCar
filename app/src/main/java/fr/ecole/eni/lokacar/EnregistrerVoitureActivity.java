package fr.ecole.eni.lokacar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class EnregistrerVoitureActivity extends AppCompatActivity {

    private Spinner spinnerNbPlaces;
    private Spinner spinnerMotorisation;
    private Spinner spinnerType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrer_voiture);

        spinnerNbPlaces = findViewById(R.id.spinnerNbPplaces);
        String[] nbplaces = {"2","3","5","8"};
        ArrayAdapter<String> adapterSpinnerNbPlaces = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nbplaces);
        spinnerNbPlaces.setAdapter(adapterSpinnerNbPlaces);

        spinnerMotorisation = findViewById(R.id.spinnerMotorisation);
        String[] motorisation = {"essence","diesel","hybride","électrique"};
        ArrayAdapter<String> adapterMotorisation = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, motorisation);
        spinnerMotorisation.setAdapter(adapterMotorisation);

        spinnerType = findViewById(R.id.spinnerType);
        String[] type = {"SUV","Berline","monospace","sport", "break", "cabriolet"};
        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, type);
        spinnerType.setAdapter(adapterType);
    }
}
