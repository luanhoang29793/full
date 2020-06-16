package com.ait.library.model;

import java.util.Date;

public class Book {
    private int idBook;
    private String nameBook;
    private String summaryBook;
    private String nameCategory;
    private String nameImage;
    private String nameAuthor;
    private String nameProducer;
    private String imageBook;
    private  int idCategory;
    private int idAuthor;
    private int idProducer;
    private boolean isDelete;
    public Date createDay;
    public Book(){}

    public Date getCreateDay() {
        return createDay;
    }

    public void setCreateDay(Date createDay) {
        this.createDay = createDay;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public String getImageBook() {
        return imageBook;
    }

    public void setImageBook(String imageBook) {
        this.imageBook = imageBook;
    }

    public String getSummaryBook() {
        return summaryBook;
    }

    public void setSummaryBook(String summaryBook) {
        this.summaryBook = summaryBook;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getNameAuthor() {
        return nameAuthor;
    }

    public void setNameAuthor(String nameAuthor) {
        this.nameAuthor = nameAuthor;
    }



    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public String getNameProducer() {
        return nameProducer;
    }

    public void setNameProducer(String nameProducer) {
        this.nameProducer = nameProducer;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public int getIdProducer() {
        return idProducer;
    }

    public void setIdProducer(int idProducer) {
        this.idProducer = idProducer;
    }
}
