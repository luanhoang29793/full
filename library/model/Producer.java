package com.ait.library.model;

public class Producer {
    private int idproducer;
    private String nameProducer;
    private boolean isDelete;
    public Producer(){}

    public int getIdproducer() {
        return idproducer;
    }

    public void setIdproducer(int idproducer) {
        this.idproducer = idproducer;
    }

    public String getNameProducer() {
        return nameProducer;
    }

    public void setNameProducer(String nameProducer) {
        this.nameProducer = nameProducer;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}
