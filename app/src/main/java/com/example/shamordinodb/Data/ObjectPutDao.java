package com.example.shamordinodb.Data;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.shamordinodb.Objects.ObjectPut;

import java.util.List;
@Dao
public interface ObjectPutDao {
    @Query("SELECT * FROM ObjectPut")
    List<ObjectPut> getAllObjects();

    //пример запроса с выборкой
    @Query("SELECT * FROM ObjectPut WHERE id = :id")
    ObjectPut getById(int id);
}
