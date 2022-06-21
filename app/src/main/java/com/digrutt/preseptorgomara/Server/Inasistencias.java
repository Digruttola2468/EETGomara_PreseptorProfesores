package com.digrutt.preseptorgomara.Server;

public class Inasistencias {

    private String alumno;
    private String inasistencias;
    private String id;

    public Inasistencias(String id,String alumno,String inasistencia){
        this.alumno = alumno;
        this.inasistencias = inasistencia;
        this.id = id;
    }

    public String getAlumno() {
        return alumno;
    }

    public String getInasistencias() {
        return inasistencias;
    }

    public String getId(){
        return id;
    }
}
