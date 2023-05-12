package com.driver;

import java.time.LocalDate;

public class Message {
    private int id;
    private String content;
    private LocalDate timestamp;

    public Message(int messageId, String content, LocalDate now) {
        this.id=messageId;
        this.content=content;
        this.timestamp=now;
    }
}
