package com.tarea.p4di.UI;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tarea.p4di.CORE.Planta;
import com.tarea.p4di.CORE.ListaPlantasAdapter;
import com.tarea.p4di.DDBB.PlantaLab;
import com.tarea.p4di.R;

import java.util.ArrayList;


public class ListaPlantasFragment extends Fragment {
    ArrayList<Planta> listaPlantas;
    PlantaLab plantaLab;
    View rootview;
    ListaPlantasAdapter listaPlantasAdapter;
    RecyclerView recyclerViewPlantas;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_lista_plantas, container, false);
        plantaLab = PlantaLab.get(requireActivity().getApplicationContext());
        listaPlantas = (ArrayList<Planta>) plantaLab.getPlantas();
        recyclerViewPlantas = rootview.findViewById(R.id.recy_listaplantas);
        recyclerViewPlantas.setLayoutManager(new LinearLayoutManager(requireActivity().getApplicationContext(), RecyclerView.VERTICAL, false));
        listaPlantasAdapter = new ListaPlantasAdapter(getContext(), listaPlantas);
        recyclerViewPlantas.setAdapter(listaPlantasAdapter);
        return rootview;
    }
}
