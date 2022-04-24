package com.example.springcliente.conexion;

import android.app.Activity;
import android.content.Context;

import com.example.springcliente.Entidades.Estudiante;

public class Centralizador {
    private static final String url ="http://192.168.1.237:8080";
    public static void getEstudiantes(Activity act, Context con){
        new ObtenerEstudiantes(url, act, con).execute();
    }
    public static void crearestudiante(Activity act, Context con, Estudiante est){
        new CrearEstudiante(url, act, con, est).execute();
    }
}
