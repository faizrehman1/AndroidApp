package com.example.adnan.panachatfragment.Activities;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudinary.Transformation;
import com.cloudinary.Uploader;
import com.cloudinary.utils.ObjectUtils;
import com.example.adnan.panachatfragment.Adaptors.adaptorMessages;
import com.example.adnan.panachatfragment.CallScreenActivity;
import com.example.adnan.panachatfragment.PlaceCallActivity;
import com.example.adnan.panachatfragment.R;
import com.example.adnan.panachatfragment.Signatures.signature_msgs;
import com.example.adnan.panachatfragment.SinchService;
import com.example.adnan.panachatfragment.UTils.Global;
import com.example.adnan.panachatfragment.UTils.Utils;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.sinch.android.rtc.SinchError;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import github.ankushsachdeva.emojicon.EmojiconEditText;
import github.ankushsachdeva.emojicon.EmojiconGridView;
import github.ankushsachdeva.emojicon.EmojiconsPopup;
import github.ankushsachdeva.emojicon.emoji.Emojicon;

public class ChatActivity extends AppCompatActivity implements SinchService.StartFailedListener, ServiceConnection {
    EmojiconEditText emojiconEditText;
    ImageButton btnSend, smilie;
    String partnerPictureString;
    String userId = Global.uid;
    ImageView call;
    View v;
    String partnerId = Global.PartnaerId;
    Firebase fire = new Firebase(Global.url);
    String amPm;
    int i = 0;
    private SinchService.SinchServiceInterface mSinchServiceInterface;

    public static Activity acti;
    adaptorMessages adaptor;
    ListView listView;
    ArrayList<signature_msgs> msgs;
    ImageButton photoButton;
    Uri uri;
    View rootView;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main_chat);
        setRequestedOrientation(ActivityInfo
                .SCREEN_ORIENTATION_PORTRAIT);
        msgs = new ArrayList<>();
        acti = ChatActivity.this;
        adaptor = new adaptorMessages(this, msgs);
        this.bindService(new Intent(this, SinchService.class), this, this.BIND_AUTO_CREATE);
//        v = View.inflate(this, R.layout.activity_after_login_screen, null);
        call = (ImageView) findViewById(R.id.ActivityimageView);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChatActivity.this, PlaceCallActivity.class);
                startActivity(i);
                finish();
            }
        });
        TextView name = (TextView) findViewById(R.id.activityName);
        name.setText(Global.partnerName);
        Uploader uploader = Utils.cloudinary().uploader();
        smilie = (ImageButton) findViewById(R.id.smilies);
        rootView = findViewById(R.id.rootView);
        btnSend = (ImageButton) findViewById(R.id.mGroupChatSentButton);
        emojicon();
        photoButton = (ImageButton) findViewById(R.id.mGroupChatPicButton);
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager check = (ConnectivityManager)
                        ChatActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo[] info = check.getAllNetworkInfo();

                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        Intent ii = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(ii, 0);
                        Toast.makeText(ChatActivity.this, "Wait while uploading ....", Toast.LENGTH_SHORT).show();
                    } else {
                        Snackbar.make(photoButton, "No Connectivity", Snackbar.LENGTH_SHORT).show();


                    }
                }

            }
        });

        listView = (ListView) findViewById(R.id.listView);
        listView.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        listView.setStackFromBottom(true);
        listView.setAdapter(adaptor);
//        registerForContextMenu(listView);

        emojiconEditText = (EmojiconEditText) findViewById(R.id.mGroupChateditText);

        fire.child("AppData").child("Conversations").child(Global.uid).child(partnerId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("snapppp", dataSnapshot.getValue().toString());

                signature_msgs signature = dataSnapshot.getValue(signature_msgs.class);
                msgs.add(signature);
                adaptor.notifyDataSetChanged();
                Log.d("snapppp", signature.getMessage());

//                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                adaptor.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        emojiconEditText.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    btnSend.setImageResource(R.drawable.ic_chat_send);
                } else {
                    btnSend.setImageResource(R.drawable.ic_chat_send_active);
                }
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {


            }
        });


        Log.d("Dataaaa", "UserId :" + userId + "Partner Id :" + partnerId);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emojiconEditText.getText().length() > 0) {
                    Log.d("Dataaaa", "UserId :" + userId + "Partner Id :" + partnerId);
//                String time = Time();
                    String date = Date();
                    Firebase messRef = fire.child("AppData").child("Conversations").child(userId).child(partnerId);
                    Firebase messRef2 = fire.child("AppData").child("Conversations").child(partnerId).child(userId);
                    messRef.push().setValue(new signature_msgs(emojiconEditText.getText().toString(), userId, date, url));
                    messRef2.push().setValue(new signature_msgs(emojiconEditText.getText().toString(), userId, date, url));
                    url = "N/A";
                    emojiconEditText.setText("");
                } else {
                    Snackbar.make(emojiconEditText, "Plz write some text first", Snackbar.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void emojicon() {

        final EmojiconsPopup popup = new EmojiconsPopup(rootView, ChatActivity.this);

        //Will automatically set size according to the soft keyboard size
        popup.setSizeForSoftKeyboard();

        //If the emoji popup is dismissed, change emojiButton to smiley icon
        popup.setOnDismissListener(new PopupWindow.OnDismissListener() {

            @Override
            public void onDismiss() {
//                changeEmojiKeyboardIcon(emojiButton, R.drawable.smiley);
            }
        });

        //If the text keyboard closes, also dismiss the emoji popup
        popup.setOnSoftKeyboardOpenCloseListener(new EmojiconsPopup.OnSoftKeyboardOpenCloseListener() {

            @Override
            public void onKeyboardOpen(int keyBoardHeight) {

            }

            @Override
            public void onKeyboardClose() {
                if (popup.isShowing())
                    popup.dismiss();
            }
        });

        //On emoji clicked, add it to edittext
        popup.setOnEmojiconClickedListener(new EmojiconGridView.OnEmojiconClickedListener() {

            @Override
            public void onEmojiconClicked(Emojicon emojicon) {
                if (emojiconEditText == null || emojicon == null) {
                    return;
                }

                int start = emojiconEditText.getSelectionStart();
                int end = emojiconEditText.getSelectionEnd();
                if (start < 0) {
                    emojiconEditText.append(emojicon.getEmoji());
                } else {
                    emojiconEditText.getText().replace(Math.min(start, end),
                            Math.max(start, end), emojicon.getEmoji(), 0,
                            emojicon.getEmoji().length());
                }
            }
        });

        //On backspace clicked, emulate the KEYCODE_DEL key event
        popup.setOnEmojiconBackspaceClickedListener(new EmojiconsPopup.OnEmojiconBackspaceClickedListener() {

            @Override
            public void onEmojiconBackspaceClicked(View v) {
                KeyEvent event = new KeyEvent(
                        0, 0, 0, KeyEvent.KEYCODE_DEL, 0, 0, 0, 0, KeyEvent.KEYCODE_ENDCALL);
                emojiconEditText.dispatchKeyEvent(event);
            }
        });

        smilie.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!popup.isShowing()) {

                    if (popup.isKeyBoardOpen()) {
                        popup.showAtBottom();
                    }

                    else {
                        emojiconEditText.setFocusableInTouchMode(true);
                        emojiconEditText.requestFocus();
                        popup.showAtBottomPending();
                        final InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.showSoftInput(emojiconEditText, InputMethodManager.SHOW_IMPLICIT);
                    }
                }

                else {
                    popup.dismiss();
                }
            }
        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            try {
                uri = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(uri,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();



                Upload upload1 = new Upload();
                upload1.execute(picturePath);

            } catch (Exception e) {

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public String Date() {
        String date = DateFormat.getDateTimeInstance().format(new Date());
        //24 hour format


        return date;

    }

    @Override
    public void onStartFailed(SinchError error) {

    }

    @Override
    public void onStarted() {

    }


    class Upload extends AsyncTask<String, Void, HashMap<String, Object>> {

        @Override
        protected HashMap<String, Object> doInBackground(String... params) {
            File file1 = new File(params[0]);
            Map wait = null;
            try {
               /* wait =*/
                Map options = ObjectUtils.asMap("transformation", new Transformation().width(200).height(200));

                return (HashMap<String, Object>) Utils.cloudinary().uploader().upload(file1, options);
            } catch (IOException e) {
                e.printStackTrace();
                return (HashMap<String, Object>) wait;

            }


        }

        @Override
        protected void onPostExecute(HashMap<String, Object> stringObjectHashMap) {
            super.onPostExecute(stringObjectHashMap);
            try {
                url = (String) stringObjectHashMap.get("url");
            } catch (Exception e) {

            }
            Log.d("Amazing", url);
            String date = Date();
            Firebase messRef = fire.child("AppData").child("Conversations").child(userId).child(partnerId);
            Firebase messRef2 = fire.child("AppData").child("Conversations").child(partnerId).child(userId);
            messRef.push().setValue(new signature_msgs("", userId, date, url));
            messRef2.push().setValue(new signature_msgs("", userId, date, url));
            url = "N/A";
            emojiconEditText.setText("");
        }
    }


    @Override
    public void onResume() {

        super.onResume();
        Log.d("fia", "chat rsd" + Global.PartnaerId);


//        mSinchServiceInterface.startClient(Global.uid);


    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (SinchService.class.getName().equals(componentName.getClassName())) {
            mSinchServiceInterface = (SinchService.SinchServiceInterface) iBinder;
            onServiceConnected();
        }

    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        if (SinchService.class.getName().equals(componentName.getClassName())) {
            mSinchServiceInterface = null;
            onServiceDisconnected();
        }
    }

    private void onServiceConnected() {
        mSinchServiceInterface.setStartListener(this);
        if (!mSinchServiceInterface.isStarted()) {
            mSinchServiceInterface.startClient(Global.uid);
            Global.flag = true;
        }
    }

    private void onServiceDisconnected() {

    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(ChatActivity.this, AfterLoginScreen.class);
        startActivity(i);
        super.onBackPressed();
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("fia", "chat start" + Global.PartnaerId);

        Global.active = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("fia", "chat stop" + Global.PartnaerId);

//        Global.active = false;
    }

    @Override
    public void onDestroy() {
        Global.active = false;
        Log.d("fia", "chat destroy" + Global.PartnaerId);
        super.onDestroy();

    }

    @Override
    public void onPause() {
        Global.active = true;
        super.onPause();

    }
}
