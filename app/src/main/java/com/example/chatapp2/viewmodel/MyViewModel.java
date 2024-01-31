package com.example.chatapp2.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.chatapp2.Repository.Repository;
import com.example.chatapp2.model.ChatGroup;
import com.example.chatapp2.model.ChatMessage;

import java.util.List;

public class MyViewModel extends AndroidViewModel {


    Repository repository;


    public MyViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
    }

    // Auth
    public void signUpAnonymousUser() {
        Context c = this.getApplication(); //AVM allows us to use this context
        repository.firebaseAnonymousAuth(c);
    }

    public String getCurrentUserId() {
        return repository.getCurrentUserId();
    }

    public void signOut() {
        repository.signOUT();
    }
    // Getting Chat Groups
    public MutableLiveData<List<ChatGroup>> getGroupList(){
        return repository.getChatGroupMutableLiveData();
    }

    public void createNewGroup(String groupName){
        repository.createNewChatGroup(groupName);
    }


    // Messages
    public MutableLiveData<List<ChatMessage>> getMessagesLiveData(String groupName){
        return repository.getMessagesLiveData(groupName);
    }

    public void sendMessage(String msg, String chatGroup){
        repository.sendMessage(msg,chatGroup);
    }



}
