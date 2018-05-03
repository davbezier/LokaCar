package fr.ecole.eni.lokacar;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import fr.ecole.eni.lokacar.dal.LocationDAL;
import fr.ecole.eni.lokacar.modeles.Client;
import fr.ecole.eni.lokacar.modeles.Location;
import fr.ecole.eni.lokacar.modeles.Voiture;

public class LocationActivity extends AppCompatActivity {

    private EditText mEditDateDebut;
    private EditText mEditDateFin;
    private EditText mEditPrix;
    private LocationDAL mLocationDAL;
    private Boolean isFormLocationCompleted;
    private String dateDebut;
    private String dateFin;
    private String prixTotal;
    private Client mClient;
    private Voiture mVoiture;
    private Location mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        mEditDateDebut = (EditText) findViewById(R.id.edit_date_debut);
        mEditDateFin = (EditText) findViewById(R.id.edit_date_fin);
        mEditPrix = (EditText) findViewById(R.id.edit_prix);

        mClient = (Client) getIntent().getParcelableExtra("objetClient");
        mVoiture = (Voiture) getIntent().getParcelableExtra("voitureObjet");

    }

    public void onBtnEnregistrerLocation(View view) {

        isFormLocationCompleted = true;

        dateDebut = mEditDateDebut.getText().toString();

        if (dateDebut == null || dateDebut.isEmpty()) {
            String dateDebutVide = "Veuillez renseigner la date de début";
            Toast toast = Toast.makeText(LocationActivity.this, dateDebutVide, Toast.LENGTH_LONG);
            TextView textView = (TextView) toast.getView().findViewById(android.R.id.message);
            textView.setTextColor(Color.RED);
            toast.getView().setBackgroundColor(Color.WHITE);
            toast.show();
            isFormLocationCompleted = false;
        }

        dateFin = mEditDateFin.getText().toString();
        if (dateFin == null || dateFin.isEmpty()) {
            String dateFinVide = "Veuillez renseigner la date de fin";
            Toast toast = Toast.makeText(LocationActivity.this, dateFinVide, Toast.LENGTH_LONG);
            TextView textView = (TextView) toast.getView().findViewById(android.R.id.message);
            textView.setTextColor(Color.RED);
            toast.getView().setBackgroundColor(Color.WHITE);
            toast.show();
            isFormLocationCompleted = false;
        }

        prixTotal = mEditPrix.getText().toString();
        if (prixTotal == null || prixTotal.isEmpty()) {
            String prixVide = "Veuillez renseigner le prix";
            Toast toast = Toast.makeText(LocationActivity.this, prixVide, Toast.LENGTH_LONG);
            TextView textView = (TextView) toast.getView().findViewById(android.R.id.message);
            textView.setTextColor(Color.RED);
            toast.getView().setBackgroundColor(Color.WHITE);
            toast.show();
            isFormLocationCompleted = false;
        }

        if (isFormLocationCompleted) {

            Double prix = Double.parseDouble(prixTotal);

            mLocation = new Location(dateDebut, dateFin, prix, mClient, mVoiture, false);

            Log.v("location", mLocation.toString());

            mLocationDAL = new LocationDAL(LocationActivity.this);

           // mLocationDAL.insertLocation(mLocation);
            InsertLocation insertLocation = new InsertLocation();
            insertLocation.execute(mLocation);
        }
    }

    private class InsertLocation extends AsyncTask<Location, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Location... locations) {
            try {
                mLocationDAL.insertLocation(locations[0]);
                return true;
            } catch (Exception e) {
                return false;
            }

        }

        @Override
        protected void onPostExecute(Boolean isRegistered) {
            super.onPostExecute(isRegistered);

            if (isRegistered){
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(LocationActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(LocationActivity.this);
                }
                builder.setTitle("Réussi")
                        .setMessage("L'enregistrement de la location a bien été prise en compte")
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LocationActivity.this.finish();
                                Intent intent = new Intent(LocationActivity.this, AccueilActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();
            } else {
                Toast toast = Toast.makeText(LocationActivity.this, "Problème lors de l'enregistrement de la location", Toast.LENGTH_LONG);
                toast.show();
            }

        }
    }
}
