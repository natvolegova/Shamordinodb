package com.example.shamordinodb.Data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.shamordinodb.Objects.Highload;
import com.example.shamordinodb.Objects.Category;
import com.example.shamordinodb.Objects.Element;
import com.example.shamordinodb.Objects.ObjectPut;
import com.example.shamordinodb.Objects.Calendar;


@Database(entities = {Category.class, Element.class, Highload.class, ObjectPut.class, Calendar.class}, version = 1, exportSchema = false)
public abstract class DatabaseHelper extends RoomDatabase {

    public abstract CategoryDao CategoryDao();
    public abstract ElementDao ElementDao();
    public abstract HighloadDao HighloadDao();
    public abstract ObjectPutDao ObjectPutDao();
    public abstract CalendarDao CalendarDao();
}