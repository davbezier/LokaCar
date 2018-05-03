package fr.ecole.eni.lokacar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.List;

import fr.ecole.eni.lokacar.dal.GerantDAL;
import fr.ecole.eni.lokacar.modeles.Gerant;


/**
 * Ecran de login permettant la connexion à l'application via login/mot de passe
 */
public class LoginActivity extends AppCompatActivity {

    private AutoCompleteTextView mLoginView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private GerantDAL mGerantDAL;
    private Gerant mGerant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       mGerant = new Gerant("gerant1", "mdp", "Nantes");

       mGerantDAL = new GerantDAL(LoginActivity.this);
       mGerantDAL.insertGerant(mGerant);

        mLoginView = (AutoCompleteTextView) findViewById(R.id.login);
        mPasswordView = (EditText) findViewById(R.id.password);

        mLoginFormView = (View) findViewById(R.id.login_form);
        mProgressView = (View) findViewById(R.id.login_page);

        Button mSignIn = (Button) findViewById(R.id.sign_in);
        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mGerantDAL.validerConnexion(mLoginView.getText().toString(), mPasswordView.getText().toString())) {

                    Intent intent = new Intent(LoginActivity.this, AccueilActivity.class);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(LoginActivity.this, "Login ou mot de passe incorrect", Toast.LENGTH_LONG);
                    TextView toastTextView = (TextView) toast.getView().findViewById(android.R.id.message);
                    toastTextView.setTextColor(Color.RED);
                    toast.getView().setBackgroundColor(Color.WHITE);
                    toast.show();
                }

            }
        });


    }
}
