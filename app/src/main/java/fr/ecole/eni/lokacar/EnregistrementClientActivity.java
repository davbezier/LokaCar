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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import fr.ecole.eni.lokacar.dal.ClientDAL;
import fr.ecole.eni.lokacar.modeles.Client;
import fr.ecole.eni.lokacar.modeles.Location;
import fr.ecole.eni.lokacar.modeles.Voiture;

public class EnregistrementClientActivity extends AppCompatActivity {

    private TextView mTextNomVoiture;
    private TextView mTextMarqueVoiture;
    private TextView mTextImmatriculationVoiture;
    private Spinner mSpinnerClient;
    private EditText mNomClient;
    private EditText mPrenomClient;
    private EditText mAdresseClient;
    private EditText mTelephoneClient;
    private EditText mEmailClient;
    private ClientDAL mClientDAL;
    private Voiture mVoiture;
    private String modeleVoiture;
    private String marqueVoiture;
    private String immatriculationVoiture;
    private List<Client> mClientList;
    private Boolean isFormClientCompleted;
    private String clientNom;
    private String clientPrenom;
    private String clientAdresse;
    private String clientTelephone;
    private String clientEmail;
    private Client mClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrement_client);

        mClientDAL = new ClientDAL(EnregistrementClientActivity.this);

        mTextNomVoiture = (TextView) findViewById(R.id.nom_voiture);
        mTextMarqueVoiture = (TextView) findViewById(R.id.marqueVoitureClientSaved);
        mTextImmatriculationVoiture = (TextView) findViewById(R.id.immatriculationVoitureClientSaved);

        if (savedInstanceState == null){
            Bundle extra = getIntent().getExtras();
            if (extra == null) {
                modeleVoiture = null;
                marqueVoiture = null;
                immatriculationVoiture = null;
            } else {
                modeleVoiture = extra.getString("nomVoitureLoc");
                marqueVoiture = extra.getString("marqueVoitureLoc");
                immatriculationVoiture = extra.getString("immatriculationVoitureLoc");
            }
        } else {
            modeleVoiture = (String) savedInstanceState.getSerializable("nomVoitureLoc");
            marqueVoiture = (String) savedInstanceState.getSerializable("marqueVoitureLoc");
            immatriculationVoiture = (String) savedInstanceState.getSerializable("immatriculationVoitureLoc");
        }

        mVoiture = (Voiture) getIntent().getParcelableExtra("objetVoiture");

        mTextNomVoiture.setText(modeleVoiture);
        mTextMarqueVoiture.setText(marqueVoiture);
        mTextImmatriculationVoiture.setText(immatriculationVoiture);



        mSpinnerClient = (Spinner) findViewById(R.id.clients_enregistre);
        ClientDAL clientDAL = new ClientDAL(EnregistrementClientActivity.this);

        mClientList = clientDAL.getAllClient();

        List<String> listeNomClient = new ArrayList<>();

        for (Client client : mClientList) {
            listeNomClient.add(client.getNom());
        }

        ArrayAdapter<String> clientArrayAdapter = new ArrayAdapter<String>(EnregistrementClientActivity.this,
                android.R.layout.simple_spinner_item, listeNomClient);

        clientArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinnerClient.setAdapter(clientArrayAdapter);

        mSpinnerClient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String clientSelectionne = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        mNomClient = (EditText) findViewById(R.id.nom_client);
        mPrenomClient = (EditText) findViewById(R.id.prenom_client);
        mAdresseClient = (EditText) findViewById(R.id.adresse_client);
        mTelephoneClient = (EditText) findViewById(R.id.telephone_client);
        mEmailClient = (EditText) findViewById(R.id.email_client);



    }


    public void onBtnEnregistrerLocationClient(View view) {

        isFormClientCompleted = true;

        clientNom = mNomClient.getText().toString();

        if (clientNom == null || clientNom.isEmpty()) {
            String nomVide = "Veuillez renseigner le nom";
            Toast toast = Toast.makeText(EnregistrementClientActivity.this, nomVide, Toast.LENGTH_LONG);
            TextView textView = (TextView) toast.getView().findViewById(android.R.id.message);
            textView.setTextColor(Color.RED);
            toast.getView().setBackgroundColor(Color.WHITE);
            toast.show();
            isFormClientCompleted = false;
        }

        clientPrenom = mPrenomClient.getText().toString();
        if (clientPrenom == null || clientPrenom.isEmpty()){
            String prenomVide = "Veuillez renseigner le prenom";
            Toast toast = Toast.makeText(EnregistrementClientActivity.this, prenomVide, Toast.LENGTH_LONG);
            TextView textView = (TextView) toast.getView().findViewById(android.R.id.message);
            textView.setTextColor(Color.RED);
            toast.getView().setBackgroundColor(Color.WHITE);
            toast.show();
            isFormClientCompleted = false;
        }

        clientAdresse = mAdresseClient.getText().toString();
        if (clientAdresse == null || clientAdresse.isEmpty()){
            String adresseVide = "Veuillez renseigner l'adresse";
            Toast toast = Toast.makeText(EnregistrementClientActivity.this, adresseVide, Toast.LENGTH_LONG);
            TextView textView = (TextView) toast.getView().findViewById(android.R.id.message);
            textView.setTextColor(Color.RED);
            toast.getView().setBackgroundColor(Color.WHITE);
            toast.show();
            isFormClientCompleted = false;
        }

        clientTelephone = mTelephoneClient.getText().toString();
        if (clientTelephone == null || clientAdresse.isEmpty()){
            String telephoneVide = "Veuillez renseigner le telephone";
            Toast toast = Toast.makeText(EnregistrementClientActivity.this, telephoneVide, Toast.LENGTH_LONG);
            TextView textView = (TextView) toast.getView().findViewById(android.R.id.message);
            textView.setTextColor(Color.RED);
            toast.getView().setBackgroundColor(Color.WHITE);
            toast.show();
            isFormClientCompleted = false;
        }

        clientEmail = mEmailClient.getText().toString();
        if (clientEmail == null || clientEmail.isEmpty()){
            String emailVide = "Veuillez renseigner l'email";
            Toast toast = Toast.makeText(EnregistrementClientActivity.this, emailVide, Toast.LENGTH_LONG);
            TextView textView = (TextView) toast.getView().findViewById(android.R.id.message);
            textView.setTextColor(Color.RED);
            toast.getView().setBackgroundColor(Color.WHITE);
            toast.show();
            isFormClientCompleted = false;
        }

        if (isFormClientCompleted) {
            mClient = new Client(clientNom, clientPrenom, clientAdresse, clientTelephone, clientEmail);
            InsertClient insertClient = new InsertClient();
            insertClient.execute(mClient);

        } else {
            Log.v("client", "problème");
        }


    }

    private class InsertClient extends AsyncTask<Client, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(Client... clients) {
            try{

               long idClient = mClientDAL.insertClient(clients[0]);
               mClient.setIdClient(idClient);

                return true;
            }catch (Exception e){
                return false;
            }

        }

        @Override
        protected void onPostExecute(Boolean isRegistered) {
            super.onPostExecute(isRegistered);

            if (isRegistered) {

                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    builder = new AlertDialog.Builder(EnregistrementClientActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(EnregistrementClientActivity.this);
                }
                builder.setTitle("Réussi")
                        .setMessage("L'enregistrement du client a bien été prise en compte, vous allez passer à l'enregistrement de la location")
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EnregistrementClientActivity.this.finish();
                                Intent intent = new Intent(EnregistrementClientActivity.this, LocationActivity.class);
                                intent.putExtra("objetClient", mClient);
                                intent.putExtra("voitureObjet", mVoiture);
                                startActivity(intent);
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();
            } else {
                Toast toast = Toast.makeText(EnregistrementClientActivity.this, "Problème lors de l'enregistrement du client", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }
}
