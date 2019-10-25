package com.example.shamordinodb.Data;

import androidx.room.Dao;
import androidx.room.Query;
import com.example.shamordinodb.Objects.Highload;

import java.util.List;
@Dao
public interface HighloadDao {
    @Query("SELECT * FROM Highload")
    List<Highload> getAllHighload();

    //пример запроса с выборкой
    @Query("SELECT * FROM Highload WHERE id IN (SELECT id FROM Highload ORDER BY RANDOM() LIMIT 1)")
    Highload getRandomItem();
}
