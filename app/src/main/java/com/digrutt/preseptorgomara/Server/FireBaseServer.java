package com.digrutt.preseptorgomara.Server;

import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.digrutt.preseptorgomara.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*Funciones de esta clase
 *   DE CUALQUIER CURSO O AÑO PODEMOS: CRUD
 *       +Crear un nuevo Alumno
 *       +Obtener Todos los Alumnos
 *       +Actualizar el Nombre del alumno
 *       +Actualizar inasistencia del alumno
 *       +
 * */

public class FireBaseServer {

    private static final String TAG = "FireBaseServer";
    private static FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void ObtenerBaseDeDatos(String curso){
        db.collection("Cursos/" + curso +"/Alumnos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()){
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        }else Log.w(TAG,"Error getting documents." , task.getException());

                    }
                });
    }

    public void ActualizarNombre(String id,String nombre,String curso){
        DocumentReference reference = db.collection("Cursos/" + curso +"/Alumnos").document(id);
        reference.update("Nombre",nombre).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d(TAG, "DocumentSnapshot successfully updated!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error updating document", e);
            }
        });
    }

    public void ActualizarInasistencias(String id,float Inasistencia,String curso){
        DocumentReference reference = db.collection("Cursos/" + curso + "/Alumnos").document(id);
        reference.update("Inasistencias", Inasistencia).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d(TAG, "DocumentSnapshot successfully updated!");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error updating document", e);
            }
        });
    }

    public void CreateDocumentAlumno(String DNI,String nombre,float inasistenicas,String curso){
        DocumentReference reference = db.collection("Cursos/" + curso + "/Alumnos").document(DNI);
        Map newAlumno = new HashMap();
        newAlumno.put("Nombre",nombre);
        newAlumno.put("Inasistencias",inasistenicas);
        reference.set(newAlumno)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG,"Creado Correctamente");
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG,"Error al crear el alumno",e);
            }
        });
    }

    public void obtenerTodosAlumnos(String curso){
        List<AlumnoServerFireBase> alumnoServerFireBases = new ArrayList<>();

        db.collection("Cursos/" + curso +"/Alumnos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()){
                                String nombre = String.valueOf(document.getData().get("Nombre"));
                                Float inasistencias = Float.parseFloat(String.valueOf(document.getData().get("Inasistencias")));
                                Log.d(TAG, document.getId() + " => " + "Nombre: " + nombre + " Inasistencias: " + inasistencias);
                                //alumnoServerFireBases.add(new AlumnoServerFireBase());
                            }
                        }else Log.w(TAG,"Error getting documents." , task.getException());

                    }
                });

    }

}
