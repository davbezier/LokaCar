package fr.ecole.eni.lokacar;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
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
import android.widget.TextView;
import android.widget.Toast;

import java.security.KeyStore;
import java.text.ParseException;

import fr.ecole.eni.lokacar.dal.VoitureDAL;
import fr.ecole.eni.lokacar.modeles.Voiture;

public class EnregistrerVoitureActivity extends AppCompatActivity {

    private EditText txtMarque, txtModele, txtImmatriculation;
    private RadioGroup radioGroupNbPortes, radioGroupClimatisation, radioGroupBoiteVitesse;
    private RadioButton radioButton3Portes, radioButton5Portes, radioButtonClimatisationOui,
            radioButtonClimatisationNon, radioButtonBoiteManuelle, radioButtonBoiteAuto;

    private Spinner spinnerNbPlaces;
    ArrayAdapter<String> adapterSpinnerNbPlaces;
    private Spinner spinnerMotorisation;
    ArrayAdapter<String> adapterMotorisation;
    private Spinner spinnerType;
    ArrayAdapter<String> adapterType;


    private String marque;
    private String modele;
    private String type;
    private String immatriculation;
    private Integer nbPlaces;
    private Integer nbPortes;
    private String motorisation;
    private Boolean isClimatisation;
    private Boolean isManuelle;
    private Boolean isFormCompleted;
    private VoitureDAL voitureDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrer_voiture);


        voitureDao = new VoitureDAL(EnregistrerVoitureActivity.this);

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
        adapterSpinnerNbPlaces = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listNbplaces);
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
        adapterMotorisation = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listeMotorisation);
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
        adapterType = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listeType);
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

        isFormCompleted = true;

        marque = txtMarque.getText().toString();


        if (marque == null || marque.isEmpty()) {
            String marqueVide = "Veuillez renseigner la marque";
            Toast toast = Toast.makeText(EnregistrerVoitureActivity.this, marqueVide, Toast.LENGTH_LONG);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.RED);
            toast.getView().setBackgroundColor(Color.WHITE);
            toast.show();
            isFormCompleted = false;
        }


        modele = txtModele.getText().toString();
        if (modele == null || modele.isEmpty()) {
            String modeleVide = "Veuillez renseigner le modèle";
            Toast toast = Toast.makeText(EnregistrerVoitureActivity.this, modeleVide, Toast.LENGTH_LONG);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.RED);
            toast.getView().setBackgroundColor(Color.WHITE);
            toast.show();

            isFormCompleted = false;
        }

        immatriculation = txtImmatriculation.getText().toString();
        if (immatriculation == null || immatriculation.isEmpty()) {
            String immatriculationVide = "Veuillez renseigner l'immatriculation";
            Toast toast = Toast.makeText(EnregistrerVoitureActivity.this, immatriculationVide, Toast.LENGTH_LONG);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.RED);
            toast.getView().setBackgroundColor(Color.WHITE);
            toast.show();
            isFormCompleted = false;
        }

        if (radioGroupNbPortes.getCheckedRadioButtonId() == -1) {
            String nbrePortesVide = "Veuillez renseigner le nombre de portes";
            Toast toast = Toast.makeText(EnregistrerVoitureActivity.this, nbrePortesVide, Toast.LENGTH_LONG);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.RED);
            toast.getView().setBackgroundColor(Color.WHITE);
            toast.show();
            isFormCompleted = false;
        }


        if (radioGroupClimatisation.getCheckedRadioButtonId() == -1) {
            String climatisationVide = "Veuillez renseigner si vous désirez la climatisation";
            Toast toast = Toast.makeText(EnregistrerVoitureActivity.this, climatisationVide, Toast.LENGTH_LONG);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.RED);
            toast.getView().setBackgroundColor(Color.WHITE);
            toast.show();
            isFormCompleted = false;
        }


        if (radioGroupBoiteVitesse.getCheckedRadioButtonId() == -1) {
            String boiteVitesseVide = "Veuillez renseigner le type de boite de vitesse";
            Toast toast = Toast.makeText(EnregistrerVoitureActivity.this, boiteVitesseVide, Toast.LENGTH_LONG);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.RED);
            toast.getView().setBackgroundColor(Color.WHITE);
            toast.show();
            isFormCompleted = false;
        }

        if (isFormCompleted) {
            //on envoie l'objet en BDD
            Voiture voiture = new Voiture(marque, modele, type, immatriculation, nbPlaces, nbPortes, motorisation, isClimatisation, isManuelle);
            Log.v("voiture", voiture.toString());
            InsertVoiture insertVoiture = new InsertVoiture();
            insertVoiture.execute(voiture);

        } else {
            Log.v("voiture", "problème");
        }

    }


    public void onRadioButtonNbPortesClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radio_3portes:                if (checked)
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

    private class InsertVoiture extends AsyncTask<Voiture, Integer, Boolean> {


        @Override
        protected Boolean doInBackground(Voiture... voitures) {
            try {
                voitureDao.insertVoiture(voitures[0]);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean isRegistered) {
            super.onPostExecute(isRegistered);

            if (isRegistered) {

                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(EnregistrerVoitureActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(EnregistrerVoitureActivity.this);
                }
                builder.setTitle("Enregistrement réussi")
                        .setMessage("Souhaitez vous enregistrer une nouvelle voiture?")
                        .setPositiveButton("oui", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // on remet le formulaire à zéro

                                resetForm();
                            }
                        })
                        .setNegativeButton("non", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //on ferme l'activité et on retourne à l'écran d'accueil
                                EnregistrerVoitureActivity.this.finish();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            } else {
                Toast toast = Toast.makeText(EnregistrerVoitureActivity.this, "Problème lors de l'enregistrement de la voiture", Toast.LENGTH_LONG);
                toast.show();
            }
        }

    }

    public void resetForm() {
        txtMarque.setText("");
        txtModele.setText("");
        txtImmatriculation.setText("");
        radioGroupNbPortes.clearCheck();
        radioGroupClimatisation.clearCheck();
        radioGroupBoiteVitesse.clearCheck();
        spinnerType.setAdapter(adapterType);
        spinnerNbPlaces.setAdapter(adapterSpinnerNbPlaces);
        spinnerMotorisation.setAdapter(adapterMotorisation);
    }
}