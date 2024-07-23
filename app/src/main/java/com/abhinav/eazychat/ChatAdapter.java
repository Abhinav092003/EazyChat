package com.abhinav.eazychat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abhinav.eazychat.Models.MessageModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter{
    ArrayList<MessageModel> msgModel;
    Context context;
    int SENDER_VIEW_TYPE=1;
    int RECIEVER_VIEW_TYPE=2;

    public ChatAdapter(ArrayList<MessageModel> messagemodel, Context context) {
        this.msgModel=messagemodel;
        this.context=context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==SENDER_VIEW_TYPE){
            View view = LayoutInflater.from(context).inflate(R.layout.sample_sender,parent,false);
            return new SenderViewModel(view);
        }
        else{
            View view = LayoutInflater.from(context).inflate(R.layout.sample_reciever,parent,false);
            return new RecieverViewModel(view);
        }

    }

    @Override
    public int getItemViewType(int position) {
        if(msgModel.get(position).getUid().equals(FirebaseAuth.getInstance().getUid())){
            return SENDER_VIEW_TYPE;
        }
        else{
           return RECIEVER_VIEW_TYPE;
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageModel msg = msgModel.get(position);
        if(holder.getClass()==SenderViewModel.class){
            ((SenderViewModel)holder).sendermsg.setText(msg.getMessage());
        }
        else{
            ((RecieverViewModel)holder).recievemsg.setText(msg.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return msgModel.size();
    }

    public class RecieverViewModel extends RecyclerView.ViewHolder{
        TextView recievemsg,recieverTime;
        public RecieverViewModel(@NonNull View itemView) {
            super(itemView);

            recievemsg=itemView.findViewById(R.id.recText);
            recieverTime=itemView.findViewById(R.id.rTime);
        }
    }

    public class SenderViewModel extends RecyclerView.ViewHolder{
        TextView sendermsg,senderTime;
        public SenderViewModel(@NonNull View itemView) {
            super(itemView);

            senderTime=itemView.findViewById(R.id.sTimee);
            sendermsg=itemView.findViewById(R.id.sText);
        }
    }
}
