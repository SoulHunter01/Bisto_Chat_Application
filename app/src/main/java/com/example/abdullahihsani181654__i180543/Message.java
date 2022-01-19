package com.example.abdullahihsani181654__i180543;

import android.widget.TextView;

public class Message {


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Message(String name, String status, String message) {
        this.name = name;
        this.status = status;
        this.message = message;
    }

    private String name;
    private String status;
    private String message;

}
