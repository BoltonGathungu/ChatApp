package com.example.chatapp2.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.chatapp2.R;
import com.example.chatapp2.databinding.ActivityGroupsBinding;
import com.example.chatapp2.model.ChatGroup;
import com.example.chatapp2.viewmodel.MyViewModel;
import com.example.chatapp2.views.adapters.GroupAdapter;

import java.util.ArrayList;
import java.util.List;

public class GroupsActivity extends AppCompatActivity {

//data source
    private ArrayList<ChatGroup> chatGroupArrayList;
    //view
    private RecyclerView recyclerView;
    //adapter
    private GroupAdapter groupAdapter;
    //binding associated layout
    private ActivityGroupsBinding binding;
    //View model
    private MyViewModel myViewModel;

    //Dialog
    private Dialog chatGroupDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_groups);

        //Define the viewModel
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);

        //RecyclerView with Data binding
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

       //Setup an observer to listen for changes in a "Live Data" object
        myViewModel.getGroupList().observe(this, new Observer<List<ChatGroup>>() {
            @Override
            public void onChanged(List<ChatGroup> chatGroups) {
                //the updated data is received as "chatGroups" parameter in onChanged()
                chatGroupArrayList = new ArrayList<>();
                chatGroupArrayList.addAll(chatGroups);

                groupAdapter = new GroupAdapter(chatGroupArrayList);

                recyclerView.setAdapter(groupAdapter);
                groupAdapter.notifyDataSetChanged();


            }
        });

         binding.fab.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 showDialog();
             }
         });
    }

    public void showDialog(){
       chatGroupDialog = new Dialog(this);
       chatGroupDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

       View view = LayoutInflater.from(this).inflate(R.layout.dialog_layout, null);

       chatGroupDialog.setContentView(view);
       chatGroupDialog.show();

       Button submit = view.findViewById(R.id.submit_btn);
        EditText edt = view.findViewById(R.id.chat_group_edt);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String groupName = edt.getText().toString();

                Toast.makeText(GroupsActivity.this, "Your Chat Group" +groupName, Toast.LENGTH_SHORT).show();

                myViewModel.createNewGroup(groupName);
                chatGroupDialog.dismiss();
            }
        });
    }
}