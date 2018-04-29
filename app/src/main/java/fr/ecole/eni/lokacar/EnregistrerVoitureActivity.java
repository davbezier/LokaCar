package fr.ecole.eni.lokacar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;

import fr.ecole.eni.lokacar.modeles.Voiture;

public class EnregistrerVoitureActivity extends AppCompatActivity {

    private EditText txtMarque, txtModele, txtImmatriculation;
    private RadioGroup radioGroupNbPortes, radioGroupClimatisation, radioGroupBoiteVitesse;
    private RadioButton radioButton3Portes, radioButton5Portes, radioButtonClimatisationOui,
            radioButtonClimatisationNon, radioButtonBoiteManuelle, radioButtonBoiteAuto;

    private Spinner spinnerNbPlaces;
    private Spinner spinnerMotorisation;
    private Spinner spinnerType;

    private String marque;
    private String modele;
    private String type;
    private String immatriculation;
    private Integer nbPlaces;
    private Integer nbPortes;
    private String motorisation;
    private Boolean isClimatisation;
    private Boolean isManuelle;
    private Boolean isFormCompleted = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrer_voiture);

        txtMarque = findViewById(R.id.txtMarque);
        txtModele = findViewById(R.id.txtModele);
        txtImmatriculation = findViewById(R.id.txtImmatriculation);
        radioGroupNbPortes = findViewById(R.id.radioGroupNbPortes);
        radioButton3Portes = findViewById(R.id.radio_3portes);
        radioButton5Portes = findViewById(R.id.radio_5portes);
        radioGroupClimatisation = findViewById(R.id.radioGroupClim);
        radioButtonClimatisationOui = findViewById(R.id.radio_ClimOui);
        radioButtonClimatisationNon = findViewById(R.id.radio_ClimNon);
        radioGroupBoiteVitesse = findViewById(R.id.radioGroupBoite);
        radioButtonBoiteManuelle = findViewById(R.id.radio_vitManuelle);
        radioButtonBoiteAuto = findViewById(R.id.radio_vitAuto);

        spinnerNbPlaces = findViewById(R.id.spinnerNbPplaces);

        final String[] listNbplaces = {"2", "3", "5", "8"};
        ArrayAdapter<String> adapterSpinnerNbPlaces = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listNbplaces);
        spinnerNbPlaces.setAdapter(adapterSpinnerNbPlaces);
        spinnerNbPlaces.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String nbPlacesInString = (String) parent.getItemAtPosition(position);

                try {
                    nbPlaces = Integer.parseInt(nbPlacesInString);
                } catch (Exception e) {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerMotorisation = findViewById(R.id.spinnerMotorisation);
        String[] listeMotorisation = {"essence", "diesel", "hybride", "électrique"};
        ArrayAdapter<String> adapterMotorisation = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listeMotorisation);
        spinnerMotorisation.setAdapter(adapterMotorisation);
        spinnerMotorisation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                motorisation = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerType = findViewById(R.id.spinnerType);
        String[] listeType = {"berline", "SUV", "monospace", "sport", "break", "cabriolet"};
        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listeType);
        spinnerType.setAdapter(adapterType);
        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





    }

    public void onBtnValiderFormClicked(View view) {

        Log.v("caPasse", "ok");

        marque = txtMarque.getText().toString(); // ask if txtMarque.getText can be null;


        if (marque == null || marque.isEmpty()) {
            String marqueVide = "Veuillez renseigner la marque";
            Toast toast = Toast.makeText(EnregistrerVoitureActivity.this, marqueVide, Toast.LENGTH_LONG);
            isFormCompleted = false;
        }

        modele = txtMarque.getText().toString();
        if (modele == null || marque.isEmpty()) {
            String modeleVide = "Veuillez renseigner le modèle";
            Toast toast = Toast.makeText(EnregistrerVoitureActivity.this, modeleVide, Toast.LENGTH_LONG);
            isFormCompleted = false;
        }

        immatriculation = txtImmatriculation.getText().toString();
        if (immatriculation == null || marque.isEmpty()) {
            String immatriculationVide = "Veuillez renseigner l'immatriculation";
            Toast toast = Toast.makeText(EnregistrerVoitureActivity.this, immatriculationVide, Toast.LENGTH_LONG);
            isFormCompleted = false;
        }

        if (isFormCompleted) {
            Voiture voiture = new Voiture(marque, modele, type, immatriculation, nbPlaces, nbPortes, motorisation, isClimatisation, isManuelle);
            SaveVoiture(voiture);
            Log.v("voiture",voiture.toString());
        }else {
            Log.v("voiture","problème");
        }

    }


    public void onRadioButtonNbPortesClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radio_3portes:
                if (checked)
                    nbPortes = 3;
                break;
            case R.id.radio_5portes:
                if (checked)
                    nbPortes = 5;
                break;
        }
    }

    public void onRadioButtonClimClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radio_ClimOui:
                if (checked)
                    isClimatisation = true;
                break;
            case R.id.radio_ClimNon:
                if (checked)
                    isClimatisation = false;
                break;


        }
    }

    public void onRadioButtonBoiteVitClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radio_vitManuelle:
                if (checked)
                    isManuelle = true;
                break;
            case R.id.radio_vitAuto:
                if (checked)
                    isManuelle = false;
                break;
        }
    }

    public void SaveVoiture(Voiture voiture) {


    }
}
