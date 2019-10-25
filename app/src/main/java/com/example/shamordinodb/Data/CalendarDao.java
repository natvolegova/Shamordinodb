package com.example.shamordinodb.Data;

import androidx.room.Dao;
import androidx.room.Query;
import com.example.shamordinodb.Objects.Calendar;

import java.util.List;

@Dao
public interface CalendarDao {
    @Query("SELECT * FROM Calendar")
    List<Calendar> getAllCalendar();

    //пример запроса с выборкой
    @Query("SELECT * FROM Calendar WHERE id = :id")
    Calendar getById(int id);
}
