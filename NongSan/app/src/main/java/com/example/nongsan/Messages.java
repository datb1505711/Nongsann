package com.example.nongsan;

import java.util.Date;

public class Messages {
    private String imageURL;

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Messages(String imageURL, Date createAt, String messages, String chatWith) {
        this.imageURL = imageURL;
        this.createAt = createAt;
        this.messages = messages;
        this.chatWith = chatWith;
    }

    private Date createAt;
    private String messages;
    private String chatWith;

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getChatWith() {
        return chatWith;
    }

    public void setChatWith(String chatWith) {
        this.chatWith = chatWith;
    }

    public Messages() {
    }

    public Messages(Date createAt, String messages, String chatWith) {
        this.createAt = createAt;
        this.messages = messages;
        this.chatWith = chatWith;
    }
}
