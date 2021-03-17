package com.tarea.p4di.UI;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textview.MaterialTextView;
import com.tarea.p4di.CORE.Planta;
import com.tarea.p4di.DDBB.PlantaLab;
import com.tarea.p4di.R;

public class DetallesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        Toolbar toolbar = findViewById(R.id.customToolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        /*
        Aplica el modo oscuro o no dependiendo de las shared preferences
         */
        if (receiveData()) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }


        ImageView imageView = findViewById(R.id.imageView);
        MaterialTextView nombreComun = findViewById(R.id.nombreComun);
        MaterialTextView nombreCientifico = findViewById(R.id.nombreCientifico);
        WebView webview = findViewById(R.id.webview);

        PlantaLab myLab = PlantaLab.get(this);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Planta planta = myLab.getPlanta(id);

        imageView.setImageResource(planta.getImagen());
        nombreComun.setText(planta.getNombre());
        nombreCientifico.setText(planta.getNombreCientifico());
        webview.getSettings().setJavaScriptEnabled(true);
        /*
        Comprueba si el link en el que se hace click es de la wikipedia o no
        Si es de la wikipedia lo abre en el mismo webview sino lo abre en el navegador por defecto
         */
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (request.getUrl().toString().contains("wikipedia")) return false;

                Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
                startActivity(intent);
                return true;
            }
        });
        webview.loadUrl(planta.getURL());


    }

    /*
      Recibe los datos de las preferences para que cuando entras o sales de la activity se mantenga el modo
     */

    public boolean receiveData() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("darkmode", false);
    }
}