package com.example.healthapp_collegeproject.get_started.models;

import android.graphics.drawable.Drawable;

public class SliderItemsModel {
    private Drawable image;
    private int heading;
    private int body;
    private int position;
    private int button;

    public SliderItemsModel(Drawable image, int heading, int body, int position, int button) {
        this.image = image;
        this.heading = heading;
        this.body = body;
        this.position = position;
        this.button = button;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public int getHeading() {
        return heading;
    }

    public void setHeading(int heading) {
        this.heading = heading;
    }

    public int getBody() {
        return body;
    }

    public void setBody(int body) {
        this.body = body;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getButton() {
        return button;
    }

    public void setButton(int button) {
        this.button = button;
    }
}
