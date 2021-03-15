package com.tarea.p4di.CORE;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "planta")
public class Planta {

    @PrimaryKey
    @NonNull
    private String id;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "nombreCientifico")
    private String nombreCientifico;
    @ColumnInfo(name = "descripcion")
    private String descripcion;
    @ColumnInfo(name = "imagen")
    private int imagen;
    @ColumnInfo(name = "selected")
    private boolean selected;

    public Planta( String nombre, String nombreCientifico, String descripcion, int imagen, boolean selected) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.nombreCientifico = nombreCientifico;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.selected = selected;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreCientifico() {
        return nombreCientifico;
    }

    public void setNombreCientifico(String nombreCientifico) {
        this.nombreCientifico = nombreCientifico;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
