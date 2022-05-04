package com.digrutt.preseptorgomara.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.digrutt.preseptorgomara.Adapter.RecyclerAdapter;
import com.digrutt.preseptorgomara.R;

import java.util.ArrayList;

public class activity_preseptor extends Activity implements AdapterView.OnItemSelectedListener {

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
        // Specify the layout to use when the list of choices appears
        adapterCurso.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spCurso.setAdapter(adapterCurso);

        ArrayAdapter<CharSequence> adapterAnio = ArrayAdapter.createFromResource(this,
                R.array.anio, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterAnio.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spAnio.setAdapter(adapterAnio);

        spAnio.setOnItemSelectedListener(this);
        spCurso.setOnItemSelectedListener(this);


        recyclerManiana.setLayoutManager(new LinearLayoutManager(this));
        recyclerTarde.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<String> listDatos = new ArrayList<>();
        listDatos.add("Ivan Di gruttola");
        listDatos.add("Santiago Torres");
        listDatos.add("Cader Lara");
        listDatos.add("DI Paolo Massi");

        RecyclerAdapter adapter = new RecyclerAdapter(listDatos);
        recyclerManiana.setAdapter(adapter);
        recyclerTarde.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String getItem = parent.getSelectedItem().toString();
        Toast.makeText(this, getItem ,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
