package com.intdv.cb123;

import com.google.gson.annotations.SerializedName;

class Message {

    @SerializedName("replyToId")
    private String replytoid;
    @SerializedName("inputHint")
    private String inputhint;
    @SerializedName("text")
    private String text;
    @SerializedName("conversation")
    private Conversation conversation;
    @SerializedName("from")
    private From from;
    @SerializedName("channelId")
    private String channelid;
    @SerializedName("timestamp")
    private String timestamp;
    @SerializedName("id")
    private String id;
    @SerializedName("type")
    private String type;
    private boolean sent = false;

    public Message(String id, String text) {
        this.type = "message";
        From from = new From();
        from.setId(id);
        this.setFrom(from);
        this.text = text;
    }

    public String getReplytoid() {
        return replytoid;
    }

    public void setReplytoid(String replytoid) {
        this.replytoid = replytoid;
    }

    public String getInputhint() {
        return inputhint;
    }

    public void setInputhint(String inputhint) {
        this.inputhint = inputhint;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public From getFrom() {
        return from;
    }

    public void setFrom(From from) {
        this.from = from;
    }

    public String getChannelid() {
        return channelid;
    }

    public void setChannelid(String channelid) {
        this.channelid = channelid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public boolean isSent() {
        return sent;
    }

    public static class Conversation {
        @SerializedName("id")
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class From {
        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private String id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
