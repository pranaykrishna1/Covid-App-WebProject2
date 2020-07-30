package com.example.webproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    Button btnLogout,btnCovid, btnMaps1;
    TextToSpeech tts;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    // Setting the Locale.
                    tts.setLanguage(Locale.US);
                    tts.speak(" To view covid 19 statistics please click the first button. Click the second button to logout. ", TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });

        btnLogout = findViewById(R.id.logout);
        btnCovid = findViewById(R.id.covid);


        btnCovid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent redirToCovid = new Intent(HomeActivity.this,CovidActivity.class);
                startActivity(redirToCovid);
            }
        });


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent retToMain = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(retToMain);
            }
        });
    }
}