package lfn.java;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataModel {
    private String id;
    private String date;
    private String user;
    private String message;

    public DataModel() {
        // NO ACTION
    }

    public DataModel(String user, String message) {
        this.user = user;
        this.message = message;
    }

    public void setId() {
        String id = String.valueOf((int) (Math.random() * 9000 + 1000));
        this.id = id;
    }

    public void setDate() {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        this.date = d.format(date);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }
}
