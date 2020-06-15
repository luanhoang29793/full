package com.ait.library.model;

import org.springframework.web.multipart.MultipartFile;

public class UploadImageBook  {
    private int idImage;
    private String nameImage;
    private String urlImage;
    public UploadImageBook(){}
    public UploadImageBook(String nameImage, String urlImage) {
        this.nameImage = nameImage;
        this.urlImage = urlImage;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
