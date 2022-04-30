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

import com.digrutt.preseptorgomara.R;

public class activity_preseptor extends Activity implements AdapterView.OnItemSelectedListener {

    private Button btGuardar;
    private Spinner spCurso, spAnio;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preseptor);

        btGuardar = findViewById(R.id.bt_GuardarAsistencias);
        spCurso = findViewById(R.id.sp_curso);
        spAnio = findViewById(R.id.sp_anio);

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
