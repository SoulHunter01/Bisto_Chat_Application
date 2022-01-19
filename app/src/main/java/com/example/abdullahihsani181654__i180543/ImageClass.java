package com.example.abdullahihsani181654__i180543;
import android.graphics.drawable.Drawable;

public class ImageClass {
    public ImageClass(Drawable image, String name, String message, Drawable status,String state) {
        this.Image = image;
        this.name = name;
        this.message = message;
        this.status=status;
        this.state=state;
    }

    public Drawable getImage() {
        return Image;
    }

    public void setImage(Drawable image) {
        Image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Drawable getStatus() { return status; }

    public void setStatus(Drawable status) { this.status = status; }

    private Drawable Image;
    private String name;
    private String message;
    private Drawable status;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String state;

}
