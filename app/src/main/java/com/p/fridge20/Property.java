package com.p.fridge20;

public class Property {
    private String propertyName;
    private int imageSrc;
    private int id;

    public Property(String propertyName, int imageSrc, int id) {
        this.propertyName = propertyName;
        this.imageSrc = imageSrc;
        this.id = id;
    }


    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public int getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(int imageSrc) {
        this.imageSrc = imageSrc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
