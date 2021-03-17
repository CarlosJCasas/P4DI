package com.tarea.p4di.DDBB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.tarea.p4di.CORE.Planta;

import java.util.List;

@Dao
interface PlantaDao {
    @Query("SELECT * FROM planta")
    List<Planta> getPlantas();

    @Query("SELECT * FROM planta WHERE id LIKE :uuid")
    Planta getPlanta(String uuid);

    @Query("DELETE  FROM planta")
    void deleteAll();

    @Insert
    void addPlanta(Planta plant);

    @Delete
    void delPlanta(Planta plant);

    @Update
    void updatePlanta(Planta plant);
}
