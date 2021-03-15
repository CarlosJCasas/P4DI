package com.tarea.p4di.CORE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
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
    private TabItem plantas_tab;
    private TabLayout tabLayout2;
    private TabItem mijardin_tab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		tabLayout2 = findViewById(R.id.tabLayout2);
		plantas_tab = findViewById(R.id.plantas_tab);
		mijardin_tab = findViewById(R.id.mijardin_tab);

        myPLantaLab = PlantaLab.get(this);

        //Addplantas

        Planta arce = new Planta(getString(R.string.arceJapones), getString(R.string.arceCientifico), getString(R.string.arceJaponesDescripcion), R.drawable.acer_palmatum, false);
        Planta buganvilla = new Planta(getString(R.string.buganvilla), getString(R.string.buganvillaCientifico), getString(R.string.buganvillaDescripcion), R.drawable.buganvilla, false);
        Planta gardenia = new Planta(getString(R.string.gardenia), getString(R.string.gardeniaCientifico), getString(R.string.gardeniaDescripcion), R.drawable.gardenia, false);
        Planta girasol = new Planta(getString(R.string.girasol), getString(R.string.girasolCientifico), getString(R.string.girasolDescripcion), R.drawable.girasoles, false);
        Planta hortensia = new Planta(getString(R.string.hortensia), getString(R.string.hortensiaCientifico), getString(R.string.hortensiaDescripcion), R.drawable.hortensia, false);
        Planta jazmin = new Planta(getString(R.string.jazmin), getString(R.string.jazminsCientifico), getString(R.string.jazminDescripcion), R.drawable.jazmin, false);
        Planta orquidea = new Planta(getString(R.string.orquidea), getString(R.string.orquideaCientifico), getString(R.string.orquideaDescripcion), R.drawable.orquideas, false);
        Planta pasiflora = new Planta(getString(R.string.pasiflora), getString(R.string.pasifloraCientifico), getString(R.string.pasifloraDescripcion), R.drawable.pasiflora, false);
        Planta plumbago = new Planta(getString(R.string.plumbago), getString(R.string.plumbagoCientifico), getString(R.string.plumbagoDescripcion), R.drawable.plumbago, false);
        Planta rosa = new Planta(getString(R.string.rosa), getString(R.string.rosaCientifico), getString(R.string.rosaDescripcion), R.drawable.rosa, false);

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

    @Override
    protected void onStart() {
        super.onStart();

        tabLayout2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch (tab.getPosition()){
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
}