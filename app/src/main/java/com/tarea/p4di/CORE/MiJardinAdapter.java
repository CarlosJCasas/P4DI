package com.tarea.p4di.CORE;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tarea.p4di.R;

import java.util.ArrayList;

public class MiJardinAdapter extends RecyclerView.Adapter<MiJardinAdapter.ViewHolder> {
    ItemClickListener itemClickListener;
    LayoutInflater myLayoutInflater;
    ArrayList<Planta> listaPlantas;
    Context context;

    public MiJardinAdapter(Context context, ArrayList<Planta> listaPlantas, ItemClickListener itemClickListener) {
        this.context = context;
        this.itemClickListener = itemClickListener;
        this.myLayoutInflater = LayoutInflater.from(context);
        this.listaPlantas = listaPlantas;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Crear item
        View view = myLayoutInflater.inflate(R.layout.mi_jardin_item_adapter, parent, false);
        return new ViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Planta planta = listaPlantas.get(position);
        holder.imageView.setImageResource(planta.getImagen());
        holder.textView.setText(planta.getNombre());
    }

    @Override
    public int getItemCount() {
        return listaPlantas.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemCLick(int position);
    }


    /*
    VIEWHOLDER
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
        ItemClickListener itemClickListener;

        public ViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.mijardin_imageview);
            textView = itemView.findViewById(R.id.mijardin_textview);
            this.itemClickListener = itemClickListener;
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (itemClickListener != null) {
                itemClickListener.onItemCLick(getAdapterPosition());
            }
        }
    }
}
