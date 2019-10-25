package com.example.shamordinodb.Objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Highload {
    @PrimaryKey
    private int id;
    private String name;
    private String text_detail;

    public Highload(int id, String name, String text_detail) {
        this.id = id;
        this.name = name;
        this.text_detail = text_detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText_detail() {
        return text_detail;
    }

    public void setText_detail(String text_detail) {
        this.text_detail = text_detail;
    }
}
