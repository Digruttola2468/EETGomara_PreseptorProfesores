package com.digrutt.preseptorgomara.Server;

public class Alumno {

    private  String Nombre = "";
    private  float inasistencias = 0.0F;

    Alumno(){}

    Alumno(String nombre,float inasistencias){
        this.Nombre = nombre;
        this.inasistencias = inasistencias;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;

    }

    public float getInasistencias() {
        return inasistencias;
    }

    public void setInasistencias(float inasistencias) {
        this.inasistencias = inasistencias;
    }
}
