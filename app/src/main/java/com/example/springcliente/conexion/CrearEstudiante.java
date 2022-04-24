package com.example.springcliente.conexion;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.springcliente.Entidades.Estudiante;
import com.example.springcliente.herramientas.ConversorJson;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.text.SimpleDateFormat;

import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CrearEstudiante extends AsyncTask<Void, Void, String> {
    final MediaType MEDIA_PLAIN_TEXT_JSON = MediaType.parse("application/json");

    private String url;
    private Activity act;
    private Context con;
    private String respuesta;
    private Estudiante est;
    public CrearEstudiante(String url, Activity act, Context con, Estudiante es) {
        this.url = url;
        this.act = act;
        this.con = con;
        est = es;
    }

    @Override
    protected String doInBackground(Void... voids) {
        final OkHttpClient httpClient = new OkHttpClient();
        String objeto = null;

        try {
            objeto= ConversorJson.json(est);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if(objeto!=null) {//El header contiene autenticación, etas credenciales se definen el application.properties del proyecto Spring Boot
            Request request = new Request.Builder()
                    .url(url + "/estudiantes")
                    .addHeader("Authorization", Credentials.basic("clienteandroid", "123"))
                    .post(RequestBody.create(MEDIA_PLAIN_TEXT_JSON,
                            (objeto).getBytes()))
                    .build();

            try (Response response = httpClient.newCall(request).execute()) {

                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                // Get response body
                respuesta = response.body().string();

            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("Estudiante", "Se creó el estudiante, con mensaje: " + respuesta);
        }
        return respuesta;
    }
}
