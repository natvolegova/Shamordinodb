package com.example.shamordinodb.Objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Calendar {
    @PrimaryKey
    private int id;
    private String date_start;
    private String date_end;
    private String text_detail;


    public Calendar(int id, String date_start, String date_end, String text_detail) {
        this.id = id;
        this.date_start = date_start;
        this.date_end = date_end;
        this.text_detail = text_detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    public String getText_detail() {
        return text_detail;
    }

    public void setText_detail(String text_detail) {
        this.text_detail = text_detail;
    }
}
