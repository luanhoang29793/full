package com.ait.library.model;

public class Chapter {
    private int idChapter;
    private  String nameChapter;
    private  String nameCategory;
    private int idCategory;
    private String contentChapter;
    private String nameBook;
    private String nameAuthor;
    private int idBook;
    private boolean isDelete;
    public Chapter(){}

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public void setIdChapter(int idChapter) {
        this.idChapter = idChapter;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }

    public int getIdChapter() {
        return idChapter;
    }

    public void setIdchapter(int idchapter) {
        this.idChapter = idchapter;
    }

    public String getNameChapter() {
        return nameChapter;
    }

    public void setNameChapter(String nameChapter) {
        this.nameChapter = nameChapter;
    }

    public String getContentChapter() {
        return contentChapter;
    }

    public void setContentChapter(String contentChapter) {
        this.contentChapter = contentChapter;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}

