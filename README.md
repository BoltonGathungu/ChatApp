# Introduction
This app utilizes a variety of buildFeatures in Firebase such as [Firebase Authentication](https://firebase.google.com/docs/auth) and [Firebase Realtime database](https://https://firebase.google.com/docs/database). The app has been created following the MVVM architechture.


---


#  Activities


There are three activities: Login activity, Groups activity and Chats activity. 
## Login Activity
The Login activity makes use of firebase anonymous login authentication which is first enabled from firebase. As can be seen below:

![anonymous_login](https://hackmd.io/_uploads/rk0s7Sv5p.png)

## Groups Activity

The Groups activity has a floating action bar as one of the widgets. This floating action bar is used to add a new group using a dialog box that pops up. 

The dialog box is first initialized as follows:

```
private Dialog chatGroupDialog;
```
A showdialog method is passed in the onClick method of the fab in the Groups activity. 
The showdialog method is as follows: 

```
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
```

### Chats Activity
This activity shows all the specific messages that have been sent in each of the groups that have been added. 

In order to scroll to the last message added, a smoothScrollToPosition method is included:

```
 //Scroll to the latest message added;
                int latestPosition = myAdapter.getItemCount()-1;
                if(latestPosition > 0 ){
                    recyclerView.smoothScrollToPosition(latestPosition);
```
A boolean is used to distinguish between the text sent by the the receiver and the sender by alternating the visibility of the views as well as positioning the texts differently. 
