package com.gambi.testprayerbeads;

public class ImageObject {
    private int image;
    private int value;

    public ImageObject(int value, int image) {
        this.value = value;
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
