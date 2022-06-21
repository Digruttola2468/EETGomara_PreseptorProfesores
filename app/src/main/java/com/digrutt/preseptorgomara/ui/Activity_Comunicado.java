package com.digrutt.preseptorgomara.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.digrutt.preseptorgomara.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Activity_Comunicado extends AppCompatActivity {

    private Spinner spAnio,spCurso;
    private Button btVoler,btEnviar;
    private EditText editTitle,editContenido;

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comunicado);

        spAnio = findViewById(R.id.spAnio_comunicado);
        spCurso = findViewById(R.id.spCurso_comunicado);
        btEnviar = findViewById(R.id.btEnviar_comunicado);
        btVoler = findViewById(R.id.btVolver_comunicado);
        editTitle = findViewById(R.id.editTitle_comunicado);
        editContenido = findViewById(R.id.editContenido_comunicado);

        //Agregamos la lista de años
        String[] anio  = getResources().getStringArray(R.array.ANIO);
        ArrayAdapter<CharSequence> adapterAnio = new ArrayAdapter<>(Activity_Comunicado.this, R.layout.dropdownitem, anio);
        spAnio.setAdapter(adapterAnio);

        spAnio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] curso;
                switch (parent.getItemAtPosition(position).toString()){
                    case "-":
                        curso = new String[]{"-"};
                        break;
                    case "1":
                    case "2":
                    case "3":
                        curso = new String[]{"a","b","c","d"};
                        break;

                    case "4":
                    case "5":
                        curso = new String[]{"a", "b", "c"};
                        break;

                    case "6":
                        curso = new String[]{"a"};
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + parent.getItemAtPosition(position).toString());
                }
                ArrayAdapter<CharSequence> adapterCurso = new ArrayAdapter<>(Activity_Comunicado.this, R.layout.dropdownitem, curso);
                spCurso.setAdapter(adapterCurso);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btVoler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Activity_Comunicado.this,MainActivity.class);
                startActivity(i);
            }
        });

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isEmpty()){
                    String title = editTitle.getText().toString();
                    String contenido = editContenido.getText().toString();
                    String anio = spAnio.getSelectedItem().toString();
                    String curso = spCurso.getSelectedItem().toString();

                    Map<String,String> comuni = new HashMap<>();
                    comuni.put("title",title);
                    comuni.put("contenido",contenido);

                    db.collection("Cursos/" + anio + curso + "/Comunicados").document().set(comuni).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Activity_Comunicado.this, "Se envio Correctamente", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });

    }

    public boolean isEmpty(){
        return  editContenido.getText().toString().equals("") &&
                editTitle.getText().toString().equals("") &&
                spCurso.getSelectedItem().toString().equals("") &&
                spCurso.getSelectedItem().toString().equals("");
    }

}
