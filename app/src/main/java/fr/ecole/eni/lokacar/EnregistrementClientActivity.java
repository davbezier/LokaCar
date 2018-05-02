package fr.ecole.eni.lokacar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import fr.ecole.eni.lokacar.dal.ClientDAL;

public class EnregistrementClientActivity extends AppCompatActivity {

    private TextView mTextNomVoiture;
    private Spinner mSpinnerClient;
    private EditText mNomClient;
    private EditText mPrenomClient;
    private EditText mAdresseClient;
    private EditText mTelephoneClient;
    private EditText mEmailClient;
    private Button mBoutonEnregistrer;
    private ClientDAL mClientDAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrement_client);

        mClientDAL = new ClientDAL(EnregistrementClientActivity.this);

        mTextNomVoiture = (TextView) findViewById(R.id.nom_voiture);
        mSpinnerClient = (Spinner) findViewById(R.id.clients_enregistre);
        mNomClient = (EditText) findViewById(R.id.nom_client);
        mPrenomClient = (EditText) findViewById(R.id.prenom_client);
        mAdresseClient = (EditText) findViewById(R.id.adresse_client);
        mTelephoneClient = (EditText) findViewById(R.id.telephone_client);
        mEmailClient = (EditText) findViewById(R.id.email_client);
        mBoutonEnregistrer = (Button) findViewById(R.id.enregistrer_location);

    }
}
