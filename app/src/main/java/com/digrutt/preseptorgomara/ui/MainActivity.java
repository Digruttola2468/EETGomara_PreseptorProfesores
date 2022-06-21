package com.digrutt.preseptorgomara.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.digrutt.preseptorgomara.R;
import com.digrutt.preseptorgomara.Server.FireBaseServer;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity extends AppCompatActivity {

    private Button btInasistencias,btComunicados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btInasistencias = findViewById(R.id.btInasistencias_main);
        btComunicados = findViewById(R.id.btComunicados_main);

        btInasistencias.setOnClickListener( (v) -> {
            Intent i = new Intent(MainActivity.this,Activity_Inasistencias.class);
            startActivity(i);
        } );

        btComunicados.setOnClickListener( (v) -> {
            Intent i = new Intent(MainActivity.this,Activity_Comunicado.class);
            startActivity(i);
        });

    }
}