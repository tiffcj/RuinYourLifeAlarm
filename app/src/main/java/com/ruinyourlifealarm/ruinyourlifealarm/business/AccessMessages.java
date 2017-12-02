package com.ruinyourlifealarm.ruinyourlifealarm.business;

import com.ruinyourlifealarm.ruinyourlifealarm.persistence.DatabaseHandler;
import com.ruinyourlifealarm.ruinyourlifealarm.persistence.Message;

import java.util.List;

/**
 * Created by tiffanyjiang on 2017-12-02.
 */

public class AccessMessages {
    private DatabaseHandler database;

    public AccessMessages(DatabaseHandler database) {
        this.database = database;
    }

    public void createCustomMessage(String messageName) {
        database.createMessage(messageName);
    }

    public List<Message> getAllMessages() {
        return database.getAllMessages();
    }
}
