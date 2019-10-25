package com.example.shamordinodb.Data;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import com.example.shamordinodb.Objects.Element;

@Dao
public interface ElementDao {
    @Query("SELECT * FROM Element  WHERE category_id = :category_id")
    List<Element> getAllElement(int category_id);

    //пример запроса с выборкой
    @Query("SELECT * FROM Element WHERE id = :id")
    Element getById(int id);
}
