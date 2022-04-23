package com.digrutt.preseptorgomara.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

    /*Este preseptor Gomara va a
    *
    * Agregar , Modificar , Leer y Eliminar Alumnos de Determinados Cursos
    * Administrar las inasistencias que tiene cada Alumno
    *
    * */

    private TextView nombre;
    private TextView inasistencias;
    private EditText DNI;
    private EditText curso;
    private Button botonBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.txt_nombre);
        inasistencias = findViewById(R.id.txt_inasistencias);
        DNI = findViewById(R.id.edit_dni);
        curso = findViewById(R.id.edit_curso);
        botonBuscar = findViewById(R.id.bt_buscar);

        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String doc = DNI.getText().toString();
                String cur = curso.getText().toString();

                obtenerNombre(doc,cur);
                obtenerInasistencias(doc,cur);
            }
        });
    }

    public void obtenerInasistencias(String id,String curso){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference reference = db.collection("Cursos/" + curso + "/Alumnos").document(id);
        reference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists())
                            inasistencias.setText(documentSnapshot.getData().get("Inasistencias").toString());

                        else
                            Log.w("TAG","NO EXISTE EL DOCUMENTO");
                    }
                });
    }

    public void obtenerNombre(String id,String curso){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference reference = db.collection("Cursos/" + curso + "/Alumnos").document(id);
        reference.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists())
                            nombre.setText(documentSnapshot.getData().get("Nombre").toString());

                        else
                            Log.w("TAG","NO EXISTE EL DOCUMENTO");
                    }
                });
        Log.d("TAG",nombre.getText().toString());
    }

}