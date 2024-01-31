package com.example.chatapp2.views.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp2.R;
import com.example.chatapp2.databinding.ItemCardBinding;
import com.example.chatapp2.model.ChatGroup;
import com.example.chatapp2.views.ChatActivity;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {

    private ArrayList<ChatGroup> groupArrayList;

    public GroupAdapter(ArrayList<ChatGroup> groupArrayList) {
        this.groupArrayList = groupArrayList;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemCardBinding binding = DataBindingUtil
                .inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.item_card,
                        parent,
                        false
                );
        return new GroupViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {

        //binds data to an existing ViewHolder
        //Populates the Views in the ViewHolder with Data from the Dataset

        ChatGroup currentUser = groupArrayList.get(position);
        holder.itemCardBinding.setChatGroup(currentUser);

    }

    @Override
    public int getItemCount() {
        return groupArrayList.size();
    }


    public class GroupViewHolder extends RecyclerView.ViewHolder{
       //cache reference to the individual views within an item layout
       //of a recyclerView list
       private ItemCardBinding itemCardBinding;

       public GroupViewHolder(ItemCardBinding itemCardBinding) {
           super(itemCardBinding.getRoot());
           this.itemCardBinding = itemCardBinding;

           itemCardBinding.getRoot().setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

                   int position = getAdapterPosition();
                   ChatGroup clickedChatGroup = groupArrayList.get(position);

                   Intent i = new Intent(itemView.getContext(), ChatActivity.class);
                   i.putExtra("GROUP_NAME", clickedChatGroup.getGroupName());
                   view.getContext().startActivity(i);

               }
           });
       }
   }
}
