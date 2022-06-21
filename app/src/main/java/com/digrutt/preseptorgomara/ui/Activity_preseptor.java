package com.digrutt.preseptorgomara.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.digrutt.preseptorgomara.Adapter.RecyclerAdapter;
import com.digrutt.preseptorgomara.R;
import com.digrutt.preseptorgomara.Server.Alumno;

import java.util.ArrayList;

public class Activity_preseptor extends Activity implements AdapterView.OnItemSelectedListener {

    private Button btGuardar;
    private Spinner spCurso, spAnio;

    private RecyclerView recyclerManiana;
    private RecyclerView recyclerTarde;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preseptor);

        btGuardar = findViewById(R.id.bt_GuardarAsistencias);
        spCurso = findViewById(R.id.sp_curso);
        spAnio = findViewById(R.id.sp_anio);
        recyclerManiana = findViewById(R.id.recyclerTurnoManiana);
        recyclerTarde = findViewById(R.id.recyclerTurnoTarde);

        //Mostrar la lista de Cursos y años
        ArrayAdapter<CharSequence> adapterCurso = ArrayAdapter.createFromResource(this,
        R.array.curso, android.R.layout.simple_spinner_item);
        // Especificar el tipo de layout que va a utilizar
        adapterCurso.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Aplicar esto al spinner
        spCurso.setAdapter(adapterCurso);

        ArrayAdapter<CharSequence> adapterAnio = ArrayAdapter.createFromResource(this,
                R.array.ANIO, android.R.layout.simple_spinner_item);
        adapterAnio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAnio.setAdapter(adapterAnio);

        //Espera a la escucha de una seleccion del spinner
        spAnio.setOnItemSelectedListener(this);
        spCurso.setOnItemSelectedListener(this);

        //Colocamos el tipo de alineamiento de la lista (Vertical)
        recyclerManiana.setLayoutManager(new LinearLayoutManager(this));
        recyclerTarde.setLayoutManager(new LinearLayoutManager(this));

        //Agregamos los datos
        ArrayList<String> listDatos = new ArrayList<>();
        listDatos.add("Ivan Di Gruttola");
        listDatos.add("Santiago Torres");
        listDatos.add("Cader Lara");
        listDatos.add("DI Paolo Massi");

        //Mostramos en el Recycler
        RecyclerAdapter adapter = new RecyclerAdapter(listDatos);
        recyclerManiana.setAdapter(adapter);
        recyclerTarde.setAdapter(adapter);

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ACA", String.valueOf(Alumno.AlumnosAusentes));
                //TODO Enviar una lista de los que faltaron al servidor
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d("ACA","Anio: " + spAnio.getSelectedItem().toString() + " Curso: " + spCurso.getSelectedItem().toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
