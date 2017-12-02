package com.ruinyourlifealarm.ruinyourlifealarm.persistence;

/**
 * Created by tiffanyjiang on 2017-12-02.
 */

public class Message {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Message(int id, String name) {

        this.id = id;
        this.name = name;
    }
}
