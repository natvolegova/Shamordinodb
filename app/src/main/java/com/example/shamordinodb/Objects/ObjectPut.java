package com.example.shamordinodb.Objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class ObjectPut {
    @PrimaryKey
    private int id;
    private String name;
    private String text_preview;
    private String text_detail;
    private String img_preview;
    private String img_detail;
    private String img_gallery;
    private String file_audio;
    private int sort;

    public ObjectPut(int id, String name, String text_preview, String text_detail, String img_preview, String img_detail, String img_gallery, String file_audio, int sort) {
        this.id = id;
        this.name = name;
        this.text_preview = text_preview;
        this.text_detail = text_detail;
        this.img_preview = img_preview;
        this.img_detail = img_detail;
        this.img_gallery = img_gallery;
        this.file_audio = file_audio;
        this.sort = sort;
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

    public String getImg_gallery() {
        return img_gallery;
    }

    public void setImg_gallery(String img_gallery) {
        this.img_gallery = img_gallery;
    }

    public String getFile_audio() {
        return file_audio;
    }

    public void setFile_audio(String file_audio) {
        this.file_audio = file_audio;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}