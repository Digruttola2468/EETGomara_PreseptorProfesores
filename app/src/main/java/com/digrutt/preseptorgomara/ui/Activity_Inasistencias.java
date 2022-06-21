package com.digrutt.preseptorgomara.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.digrutt.preseptorgomara.Adapter.RecyclerAdapter;
import com.digrutt.preseptorgomara.Adapter.RecyclerAdapterInasistencias;
import com.digrutt.preseptorgomara.R;
import com.digrutt.preseptorgomara.Server.Inasistencias;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Activity_Inasistencias extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button button;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inasistencias);

        button = findViewById(R.id.btVolver_inasistencias);
        recyclerView = findViewById(R.id.recyclerView_inasistencias);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_Inasistencias.this, MainActivity.class);
                startActivity(i);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Agregamos los datos
        /*ArrayList<Inasistencias> listDatos = new ArrayList<>();
        listDatos.add(new Inasistencias("Ivan Di Gruttola","6"));
        listDatos.add(new Inasistencias("Massi","11"));
        listDatos.add(new Inasistencias("Leo","13"));
        listDatos.add(new Inasistencias("Ella","15"));
        */

        ArrayList<Inasistencias> listDatos = new ArrayList<>();
        db.collection("Cursos/6a/Alumnos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()){
                                listDatos.add(new Inasistencias(document.getId(),document.getString("Apellido"),document.get("Inasistencias").toString()));
                            }
                            //Mostramos en el Recycler
                            RecyclerAdapterInasistencias adapter = new RecyclerAdapterInasistencias(listDatos);
                            recyclerView.setAdapter(adapter);
                        }

                    }
                });



    }
}
