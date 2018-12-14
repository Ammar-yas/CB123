package com.intdv.cb123;

import com.google.gson.annotations.SerializedName;

public class Conversation {
    @SerializedName("referenceGrammarId")
    private String referencegrammarid;
    @SerializedName("streamUrl")
    private String streamurl;
    @SerializedName("expires_in")
    private int expiresIn;
    @SerializedName("token")
    private String token;
    @SerializedName("conversationId")
    private String conversationid;

    public String getReferencegrammarid() {
        return referencegrammarid;
    }

    public void setReferencegrammarid(String referencegrammarid) {
        this.referencegrammarid = referencegrammarid;
    }

    public String getStreamurl() {
        return streamurl;
    }

    public void setStreamurl(String streamurl) {
        this.streamurl = streamurl;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getConversationid() {
        return conversationid;
    }

    public void setConversationid(String conversationid) {
        this.conversationid = conversationid;
    }
}
