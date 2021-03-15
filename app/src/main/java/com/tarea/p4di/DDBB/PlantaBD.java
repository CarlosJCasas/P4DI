package com.tarea.p4di.DDBB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.tarea.p4di.CORE.Planta;

@Database(entities = {Planta.class}, version = 1)
public abstract class PlantaBD extends RoomDatabase {
    public abstract PlantaDao getPlantas();
}
