package com.intdv.cb123;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private List<Message> messages;
    private Context context;

    public MessageAdapter(Context context, List<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view;
        // TODO: 12/13/2018 should be replaced with valid validation
        Toast.makeText(context, messages.get(position).isSent()+"", Toast.LENGTH_SHORT).show();
        if (messages.get(position).isSent())
            view = layoutInflater.inflate(R.layout.sent_message_item, viewGroup, false);
        else
            view = layoutInflater.inflate(R.layout.received_message_item, viewGroup, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder viewHolder, int i) {
        String messageContent = messages.get(i).getText();
        viewHolder.messageContentTV.setText(messageContent);

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void addMessages(List<Message> messages) {
        for (Message message : messages) {
            message.setSent(false);
            addMessage(message);
        }
    }

    public void addMessage(Message message) {
        int position = getItemCount();
        this.messages.add(message);
        notifyItemInserted(position);
    }


    class MessageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.messageAccountIV)
        ImageView accountImageView;
        @BindView(R.id.messageContentTV)
        TextView messageContentTV;

        public MessageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}