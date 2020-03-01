package com.developer.arsltech.uploadimagetomysqlusingvolleyphp;

public class ModelImage {
    private String id,imageurl;

    public ModelImage() {
    }

    public ModelImage(String id, String imageurl) {
        this.id = id;
        this.imageurl = imageurl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
