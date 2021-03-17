package com.tarea.p4di.UI;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabItem;
import com.tarea.p4di.CORE.MiJardinAdapter;
import com.tarea.p4di.CORE.Planta;
import com.tarea.p4di.DDBB.PlantaLab;
import com.tarea.p4di.R;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class MiJardinFragment extends Fragment implements MiJardinAdapter.ItemClickListener{
    public RecyclerView recy_misplantas;
    public ArrayList<Planta> listaPlantasCompleta;
    public ArrayList<Planta> listaPlantasSeleccionadas;
    public MiJardinAdapter myAdapter;
    public View rootview;
    public PlantaLab myPlantaLab;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myPlantaLab = PlantaLab.get(requireActivity().getApplicationContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_mi_jardin, container, false);
        listaPlantasSeleccionadas = new ArrayList<>();
        listaPlantasCompleta = (ArrayList<Planta>) myPlantaLab.getPlantas();
        for(Planta pl : listaPlantasCompleta){
            if(pl.isSelected()) listaPlantasSeleccionadas.add(pl);
        }
        recy_misplantas = rootview.findViewById(R.id.recy_misplantas);
        recy_misplantas.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recy_misplantas.setItemAnimator(new DefaultItemAnimator());
        recy_misplantas.setClickable(true);

        myAdapter = new MiJardinAdapter(getContext(), listaPlantasSeleccionadas, this);
        myAdapter.setClickListener(this);
        recy_misplantas.setAdapter(myAdapter);

        return rootview;
    }

    @Override
    public void onItemCLick(int position) {
        Planta planta = listaPlantasSeleccionadas.get(position);
        Intent intent = new Intent(requireActivity(), DetallesActivity.class);
        intent.putExtra("id", planta.getId());
        startActivity(intent);
    }
}