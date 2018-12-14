package com.intdv.cb123;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class ChatSocketListener extends WebSocketListener {
    private static final String TAG = ChatSocketListener.class.getSimpleName();
    private ChatView chatView;

    public ChatSocketListener(ChatView chatView) {
        this.chatView = chatView;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        Log.i(TAG, "onOpen: " + response.toString());
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);
        Log.i(TAG, "onMessage: " + text);
        Activities activities = new Gson().fromJson(text, Activities.class);
        if (activities.getWaterMark() == null)
            return;
        if (!activities.getWaterMark().isEmpty()) {
            chatView.onMessageReceived(activities.getMessages());
        }
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        super.onClosing(webSocket, code, reason);
        Log.i(TAG, "onClosing: " + "Code: " + code + ", Reason: " + reason);
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        super.onClosed(webSocket, code, reason);
        Log.i(TAG, "onClosed: " + "Code: " + code + ", Reason: " + reason);

    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        super.onFailure(webSocket, t, response);
        Log.e(TAG, "onFailure: " + t.getMessage() );
    }


    private class Activities {
        @SerializedName("activities")
        List<Message> messages;
        @SerializedName("watermark")
        String waterMark;

        List<Message> getMessages() {
            return messages;
        }

        String getWaterMark() {
            return waterMark;
        }
    }
}
