package com.example.shamordinodb.Objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {
    @PrimaryKey
    private int id;
    private String name;
    private String img_preview;
    private String img_detail;
    private String text_detail;

    public Category(int id, String name, String img_preview, String img_detail, String text_detail) {
        this.id = id;
        this.name = name;
        this.img_preview = img_preview;
        this.img_detail = img_detail;
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

    public String getImg_preview() {
        return img_preview;
    }

    public void setImg_preview(String img_preview) {
        this.img_preview = img_preview;
    }

    public String getImg_detail() {
        return img_detail;
    }

    public void setImg_detail(String img_detail) {
        this.img_detail = img_detail;
    }

    public String getText_detail() {
        return text_detail;
    }

    public void setText_detail(String text_detail) {
        this.text_detail = text_detail;
    }
}
