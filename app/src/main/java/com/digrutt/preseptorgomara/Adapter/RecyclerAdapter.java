package com.digrutt.preseptorgomara.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.digrutt.preseptorgomara.R;
import com.digrutt.preseptorgomara.Server.Alumno;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolderDatos> {

    private ArrayList<String> listDatos;

    public RecyclerAdapter(ArrayList<String> listDatos) {
        this.listDatos = listDatos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_recycler,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(listDatos.get(position));
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }




    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        private TextView alumno;
        private CheckBox isAusente;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            alumno = itemView.findViewById(R.id.txt_adapter_nombre);
            isAusente = itemView.findViewById(R.id.check_adapter_asistencias);

            isAusente.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Log.d("RECYCLER_ADAPTER", "Alumno: " + alumno.getText() + " Falto?: " + isChecked);
                    if(isChecked)
                        Alumno.AlumnosAusentes.add(alumno.getText().toString());
                }
            });
        }


        public void asignarDatos(String s) {
            alumno.setText(s);
        }
    }
}
