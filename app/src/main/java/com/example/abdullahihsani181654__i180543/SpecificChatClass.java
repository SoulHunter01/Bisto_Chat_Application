package com.example.abdullahihsani181654__i180543;

public class SpecificChatClass {
    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    private String Message;
    private String Time;

    public SpecificChatClass(String message, String time) {
        Message = message;
        Time = time;
    }
}
