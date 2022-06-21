package com.digrutt.preseptorgomara.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.digrutt.preseptorgomara.R;
import com.digrutt.preseptorgomara.Server.Inasistencias;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class RecyclerAdapterInasistencias extends RecyclerView.Adapter<RecyclerAdapterInasistencias.ViewHolderDatos> {

    private ArrayList<Inasistencias> listDatos;

    public RecyclerAdapterInasistencias(ArrayList<Inasistencias> listDatos) {
        this.listDatos = listDatos;
    }

    @NonNull
    @Override
    public RecyclerAdapterInasistencias.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_inasistencias_item,null,false);
        return new RecyclerAdapterInasistencias.ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.setAlumno(listDatos.get(position).getAlumno());
        holder.setInasistencias(listDatos.get(position).getInasistencias());

        holder.btInc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float aumentar = 0.5F;
                float inasistencias = Float.parseFloat(holder.getInasistencias());

                float resultado = inasistencias + aumentar;

                holder.setInasistencias(String.valueOf(resultado));

                holder.db.collection("Cursos/6a/Alumnos").document(listDatos.get(holder.getAdapterPosition()).getId()).update("Inasistencias",holder.getInasistencias()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });

            }
        });

        holder.btDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float aumentar = 0.5F;
                float inasistencias = Float.parseFloat(holder.getInasistencias());

                float resultado = inasistencias - aumentar;

                holder.setInasistencias(String.valueOf(resultado));

                holder.db.collection("Cursos/6a/Alumnos").document(listDatos.get(holder.getAdapterPosition()).getId()).update("Inasistencias",holder.getInasistencias()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }


    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        private TextView alumno;
        private TextView inasistencias;
        private Button btInc;
        private Button btDec;

        private FirebaseFirestore db = FirebaseFirestore.getInstance();
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            alumno = itemView.findViewById(R.id.txtNombre_item);
            inasistencias = itemView.findViewById(R.id.txtInasistencias_item);
            btInc = itemView.findViewById(R.id.btIncementar_item);
            btDec = itemView.findViewById(R.id.btDecrementar_item);

        }

        public String getInasistencias(){
            return inasistencias.getText().toString();
        }
        public void setInasistencias(String text){
            inasistencias.setText(text);
        }

        public void setAlumno(String stringAlumno){
            alumno.setText(stringAlumno);
        }
    }
}
