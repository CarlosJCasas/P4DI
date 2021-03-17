package com.tarea.p4di.CORE;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;
import com.tarea.p4di.DDBB.PlantaLab;
import com.tarea.p4di.R;
import com.tarea.p4di.UI.ListaPlantasFragment;
import com.tarea.p4di.UI.MiJardinFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Planta> listaPlantasCompleta = new ArrayList<>();
    private PlantaLab myPLantaLab;
    private ListaPlantasFragment listaPlantasFragment = new ListaPlantasFragment();
    private MiJardinFragment miJardinFragment = new MiJardinFragment();
    private TabLayout tabLayout2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Mi jardín");

        if (receiveData()) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        tabLayout2 = findViewById(R.id.tabLayout2);
        myPLantaLab = PlantaLab.get(this);

        /*
        Añadir plantas a la base de datos
         */

        Planta arce = new Planta(getString(R.string.arceJapones), getString(R.string.arceCientifico), getString(R.string.arceJaponesDescripcion), R.drawable.acer_palmatum, false, getString(R.string.arceJaponesURL));
        Planta buganvilla = new Planta(getString(R.string.buganvilla), getString(R.string.buganvillaCientifico), getString(R.string.buganvillaDescripcion), R.drawable.buganvilla, false, getString(R.string.bugamvillaURL));
        Planta gardenia = new Planta(getString(R.string.gardenia), getString(R.string.gardeniaCientifico), getString(R.string.gardeniaDescripcion), R.drawable.gardenia, false, getString(R.string.gardeniaURL));
        Planta girasol = new Planta(getString(R.string.girasol), getString(R.string.girasolCientifico), getString(R.string.girasolDescripcion), R.drawable.girasoles, false, getString(R.string.girasolURL));
        Planta hortensia = new Planta(getString(R.string.hortensia), getString(R.string.hortensiaCientifico), getString(R.string.hortensiaDescripcion), R.drawable.hortensia, false, getString(R.string.hortensiaURL));
        Planta jazmin = new Planta(getString(R.string.jazmin), getString(R.string.jazminsCientifico), getString(R.string.jazminDescripcion), R.drawable.jazmin, false, getString(R.string.jazminURL));
        Planta orquidea = new Planta(getString(R.string.orquidea), getString(R.string.orquideaCientifico), getString(R.string.orquideaDescripcion), R.drawable.orquideas, false, getString(R.string.orquideaURL));
        Planta pasiflora = new Planta(getString(R.string.pasiflora), getString(R.string.pasifloraCientifico), getString(R.string.pasifloraDescripcion), R.drawable.pasiflora, false, getString(R.string.pasifloraURL));
        Planta plumbago = new Planta(getString(R.string.plumbago), getString(R.string.plumbagoCientifico), getString(R.string.plumbagoDescripcion), R.drawable.plumbago, false, getString(R.string.plumbgoURL));
        Planta rosa = new Planta(getString(R.string.rosa), getString(R.string.rosaCientifico), getString(R.string.rosaDescripcion), R.drawable.rosa, false, getString(R.string.rosaURL));

        listaPlantasCompleta.add(arce);
        listaPlantasCompleta.add(buganvilla);
        listaPlantasCompleta.add(gardenia);
        listaPlantasCompleta.add(girasol);
        listaPlantasCompleta.add(hortensia);
        listaPlantasCompleta.add(jazmin);
        listaPlantasCompleta.add(orquidea);
        listaPlantasCompleta.add(pasiflora);
        listaPlantasCompleta.add(plumbago);
        listaPlantasCompleta.add(rosa);

        if (myPLantaLab.getPlantas().isEmpty()) {
            for (Planta plant : listaPlantasCompleta) {
                myPLantaLab.addPlanta(plant);
            }
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction().replace(R.id.frame_fragment, miJardinFragment);
        ft.commit();

    }

    /*
    Crea las dos tabs de Jardin y de Plantas
     */
    @Override
    protected void onStart() {
        super.onStart();

        tabLayout2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = miJardinFragment;
                        break;
                    case 1:
                        fragment = listaPlantasFragment;
                        break;
                }
                assert fragment != null;
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction().replace(R.id.frame_fragment, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    /*
    Comprueba que modo está activo, el claro o el oscuro y pone el incono en consecuencia.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        int nightModeFlags =
                this.getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;
        switch (nightModeFlags) {
            case Configuration.UI_MODE_NIGHT_YES:
                menu.getItem(0).setIcon(R.drawable.ic_baseline_dark_mode_24);
                break;

            case Configuration.UI_MODE_NIGHT_NO:
                menu.getItem(0).setIcon(R.drawable.ic_baseline_light_mode_24);
                break;

            case Configuration.UI_MODE_NIGHT_UNDEFINED:

                break;
        }

        return super.onCreateOptionsMenu(menu);
    }

    /*
    Maneja el modo al hacer
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        int nightModeFlags =
                this.getResources().getConfiguration().uiMode &
                        Configuration.UI_MODE_NIGHT_MASK;

        if (id == R.id.diaNoche) {
            if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
                item.setIcon(R.drawable.ic_baseline_light_mode_24);
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                saveData(false);

            } else if (nightModeFlags == Configuration.UI_MODE_NIGHT_NO) {
                item.setIcon(R.drawable.ic_baseline_dark_mode_24);
                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                saveData(true);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /*
    Guarda en las preferences si está en modo oscuro o no, recibe un boolean true si esta el modo oscuro activo, false para el light
     */
    public void saveData(boolean darkModeOn) {
        SharedPreferences sharedPreferences = this.getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("darkmode", darkModeOn);
        editor.apply();
    }

    /*
    Recibe los datos de las preferences para que cuando entras o sales de la activity se mantenga el modo
     */
    public boolean receiveData() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("darkmode", false);
    }
}