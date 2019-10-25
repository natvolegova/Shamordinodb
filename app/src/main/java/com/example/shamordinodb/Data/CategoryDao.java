package com.example.shamordinodb.Data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.shamordinodb.Objects.Category;

import java.util.List;

@Dao
public interface CategoryDao {

    @Query("SELECT * FROM Category")
    List<Category> getAllCategories();

    @Query("SELECT * FROM Category WHERE id IN (:idList)")
    List<Category> getCategories(Integer[] idList);

    //пример запроса с выборкой
    @Query("SELECT * FROM Category WHERE id = :id")
    Category getById(int id);
}
