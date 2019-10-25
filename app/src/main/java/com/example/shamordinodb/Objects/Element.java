package com.example.shamordinodb.Objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Element {
    @PrimaryKey
    private int id;
    private int category_id;
    private String name;
    private String text_preview;
    private String text_detail;
    private String img_preview;
    private String img_detail;
    private int sort;

    public Element(int id, int category_id, String name, String text_preview, String text_detail, String img_preview, String img_detail, int sort) {
        this.id = id;
        this.category_id = category_id;
        this.name = name;
        this.text_preview = text_preview;
        this.text_detail = text_detail;
        this.img_preview = img_preview;
        this.img_detail = img_detail;
        this.sort = sort;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText_preview() {
        return text_preview;
    }

    public void setText_preview(String text_preview) {
        this.text_preview = text_preview;
    }

    public String getText_detail() {
        return text_detail;
    }

    public void setText_detail(String text_detail) {
        this.text_detail = text_detail;
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

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
