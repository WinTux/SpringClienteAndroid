package com.example.springcliente.conexion;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.springcliente.Entidades.Estudiante;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileOutputStream;
import java.util.List;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ObtenerEstudiantes extends AsyncTask<Void, Void, String> {
    private String url;
    private Activity act;
    private Context con;
    private String respuesta;

    public ObtenerEstudiantes(String url, Activity act, Context con) {
        this.url = url;
        this.act = act;
        this.con = con;
    }
    @Override
    protected String doInBackground(Void... voids) {
        final OkHttpClient client = new OkHttpClient();//El header contiene autenticación, etas credenciales se definen el application.properties del proyecto Spring Boot
        Request request = new Request.Builder()
                .url(url + "/estudiantes")
                .addHeader("Authorization", Credentials.basic("clienteandroid", "123"))
                .build();
        try (Response response = client.newCall(request).execute()) {
            if(!response.isSuccessful())
                throw new Exception("Código no esperado: " + response);
            respuesta = response.body().string();
            FileOutputStream fos = con.openFileOutput("estudiantes.json", Context.MODE_PRIVATE);
            fos.write(respuesta.getBytes());
            fos.close();
            Log.d("APLICACION", "Contenido: " + respuesta);
            ObjectMapper mapper = new ObjectMapper();
            List<Estudiante> estudiantes = mapper.readValue(respuesta, mapper.getTypeFactory().constructCollectionType(List.class, Estudiante.class));
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
