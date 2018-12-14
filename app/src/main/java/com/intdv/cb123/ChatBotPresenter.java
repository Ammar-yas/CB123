package com.intdv.cb123;


import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class ChatBotPresenter extends BasePresenter {

    private static final String TAG = ChatBotPresenter.class.getSimpleName();
    private ChatView chatView;

    public ChatBotPresenter(ChatView chatView) {
        this.chatView = chatView;
    }

    public void startNewConversation() {
        DisposableObserver<Conversation> conversationDisposableObserver = new DisposableObserver<Conversation>() {
            @Override
            public void onNext(Conversation conversation) {
                chatView.onStartNewConversationSuccess(conversation);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "startNewConversation onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "startNewConversation onComplete: connection Closed");
            }
        };

        subscribe(getRetrofit().startNewConversation(), conversationDisposableObserver);
    }

    public void sendMessage(String conversationId, Message message) {
        Observer<Object> conversationDisposableObserver = new Observer<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object o) {
                chatView.onSendMessageSuccess();
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "sendMessage onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "sendMessage onComplete: connection Closed");
            }
        };

        subscribe(getRetrofit().sendMessage(conversationId, message), conversationDisposableObserver);
    }
}
