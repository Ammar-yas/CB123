package com.intdv.cb123;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class MainActivity extends AppCompatActivity implements ChatView {

    @BindView(R.id.messageRV)
    RecyclerView messageRV;
    @BindView(R.id.messageET)
    TextInputEditText messageET;


    private ArrayList<Message> messages;
    private OkHttpClient okHttpClient;
    private WebSocket chatSocket;
    private ChatBotPresenter chatBotPresenter;
    private Conversation conversation;
    private MessageAdapter messageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initUI();
        callApiToStartConversation();
    }

    private void callApiToStartConversation() {
        chatBotPresenter = new ChatBotPresenter(this);
        chatBotPresenter.startNewConversation();
    }

    private void initUI() {
        messages = new ArrayList<>();
        messageAdapter = new MessageAdapter(this, messages);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setStackFromEnd(true);
        messageRV.setLayoutManager(layoutManager);
        messageRV.setAdapter(messageAdapter);
    }

    @Override
    public void onStartNewConversationSuccess(Conversation conversation) {
        this.conversation = conversation;
        callWebSocket();
    }

    private void callWebSocket() {
        okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(conversation.getStreamurl()).build();
        ChatSocketListener chatSocketListener = new ChatSocketListener(this);
        chatSocket = okHttpClient.newWebSocket(request, chatSocketListener);
    }

    @OnClick(R.id.sendIV)
    void sendMessage() {
        if (messageET.getText().toString().trim().isEmpty()) {
            messageET.setError("Please Enter Message!");
        } else {
            Message message = new Message("FromAndroid", messageET.getText().toString());
            message.setSent(true);
            messageAdapter.addMessage(message);
            callApiToSendMessage(message);
        }
    }

    private void callApiToSendMessage(Message message) {
        chatBotPresenter.sendMessage(conversation.getConversationid(), message);
    }

    @Override
    public void onSendMessageSuccess() {
        Toast.makeText(this, "message Sent", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMessageReceived(List<Message> messages) {
        sendToMainThread(messages);
    }

    private void sendToMainThread(final List<Message> messages) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                messageAdapter.addMessages(messages);
            }
        });
    }
}
