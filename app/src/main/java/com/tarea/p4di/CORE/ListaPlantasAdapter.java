package com.tarea.p4di.CORE;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.tarea.p4di.DDBB.PlantaLab;
import com.tarea.p4di.R;

import java.util.ArrayList;


public class ListaPlantasAdapter extends RecyclerView.Adapter<ListaPlantasAdapter.ViewHolder> {

    LayoutInflater myLayoutInflater;
    ArrayList<Planta> listaPlantas;
    Context context;

    public ListaPlantasAdapter(Context context, ArrayList<Planta> listaPlantas) {
        this.context = context;
        this.listaPlantas = listaPlantas;
        this.myLayoutInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = myLayoutInflater.inflate(R.layout.activar_plantas_adapter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Planta planta = listaPlantas.get(position);
        holder.switchMaterial.setText(planta.getNombre());
        holder.switchMaterial.setChecked(planta.isSelected());
        holder.switchMaterial.setOnCheckedChangeListener((buttonView, isChecked) -> {
            planta.setSelected(isChecked);
            listaPlantas.set(position, planta);
            PlantaLab.get(context).updatePlanta(planta);
        });
    }

    @Override
    public int getItemCount() {
        return listaPlantas.size();
    }

    /*
    VIEWHOLDER
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        SwitchMaterial switchMaterial;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            switchMaterial = itemView.findViewById(R.id.switch_planta);
        }
    }
}
