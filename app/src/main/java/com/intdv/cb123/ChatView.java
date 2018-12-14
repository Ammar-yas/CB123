package com.intdv.cb123;

import java.util.List;

interface ChatView {
    void onStartNewConversationSuccess(Conversation conversation);
    void onSendMessageSuccess();

    void onMessageReceived(List<Message> messages);
}
