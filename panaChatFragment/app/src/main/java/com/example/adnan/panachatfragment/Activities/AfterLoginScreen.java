package com.example.adnan.panachatfragment.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.cloudinary.Cloudinary;
import com.example.adnan.panachatfragment.BaseActivity;
import com.example.adnan.panachatfragment.CallScreenActivity;
import com.example.adnan.panachatfragment.Fragments.AddNewGroup;
import com.example.adnan.panachatfragment.Fragments.AllGroupsList;
import com.example.adnan.panachatfragment.Fragments.ContactsInformation;
import com.example.adnan.panachatfragment.Fragments.FindFriends;
import com.example.adnan.panachatfragment.Fragments.MyProfile;
import com.example.adnan.panachatfragment.Fragments.StatusFragment;
import com.example.adnan.panachatfragment.Fragments.signInFragment;
import com.example.adnan.panachatfragment.R;
import com.example.adnan.panachatfragment.Signatures.User;
import com.example.adnan.panachatfragment.SinchService;
import com.example.adnan.panachatfragment.UTils.FireBaseHandler;
import com.example.adnan.panachatfragment.UTils.Global;
import com.facebook.login.LoginManager;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.sinch.android.rtc.SinchError;

public class AfterLoginScreen extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener

{
    public static Context context;
    public static Activity acti;
    com.example.adnan.panachatfragment.Fragments.signInFragment signInFragment;
    FragmentManager manager;
    ImageButton addFriendButton;
    Firebase fire;
    DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = AfterLoginScreen.this;
        FireBaseHandler.getInstance();

        setContentView(R.layout.activity_after_login_screen);
        setRequestedOrientation(ActivityInfo
                .SCREEN_ORIENTATION_PORTRAIT);
        acti = AfterLoginScreen.this;
        fire = new Firebase(Global.url);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        signInFragment = new signInFragment();

        transactionOfLoginScreen();


        addFriendButton = (ImageButton) findViewById(R.id.addFriendButton);
        addFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = AfterLoginScreen.this.getSupportFragmentManager();

                android.support.v4.app.FragmentTransaction ft = manager.beginTransaction();
                FindFriends addFriendFragment = new FindFriends();

                ft.add(R.id.RelativeLayoutHomeScreen, addFriendFragment);
                ft.addToBackStack("addFriend");
//                if (manager.getBackStackEntryCount() > 1) {
//                    try {
//                        Log.d("yes","add search poped");
//                        manager.popBackStack("searchGroup", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//
//                    } catch (Exception e) {
//                    }
//                }
//                try {
//                    if (Global.statusFlag == true) {
//                        manager.popBackStack("stackStatus", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                    }
//                    if (Global.myProfileFlag == true) {
//                        manager.popBackStack("myProfile", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//
//                    }
//                    if (Global.addNewGroupFlag == true) {
//                        manager.popBackStack("addGroup", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                    }
//                    if (Global.friendProfile == true) {
//                        manager.popBackStack("friendProfile", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                    }
//                    if (Global.infoFlag == true) {
//                        manager.popBackStack("stackInfo", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//
//                    }
//                    if (Global.searchGroupFlag == true) {
//                        manager.popBackStack("searchGroup", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                    }
//
//                } catch (Exception e) {
//
//                }
                ft.commit();
            }
        });

        ImageView searchGroup = (ImageView) findViewById(R.id.searchGroups);
        searchGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = AfterLoginScreen.this.getSupportFragmentManager();

                android.support.v4.app.FragmentTransaction ft = manager.beginTransaction();
                ft.add(R.id.RelativeLayoutHomeScreen, new AllGroupsList());
                ft.addToBackStack("searchGroup");
//                if (manager.getBackStackEntryCount() > 0) {
//                    try {
//                        Log.d("yes", "add addPopes poped");
//                        manager.popBackStack("addFriend", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//
//                    } catch (Exception e) {
//                    }
//                }
//                try {
//                    if (Global.statusFlag == true) {
//                        manager.popBackStack("stackStatus", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                    }
//                    if (Global.myProfileFlag == true) {
//                        manager.popBackStack("myProfile", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//
//                    }
//                    if (Global.addNewGroupFlag == true) {
//                        manager.popBackStack("addGroup", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                    }
//                    if (Global.friendProfile == true) {
//                        manager.popBackStack("friendProfile", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                    }
//                    if (Global.addFriendFlag == true) {
//                        manager.popBackStack("addFriend", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//
//                    }
//                    if (Global.infoFlag == true) {
//                        manager.popBackStack("stackInfo", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//
//                    }
//                    if (Global.addFriendFlag == true) {
//                        manager.popBackStack("addFriend", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//
//                    }
//
//                } catch (Exception e) {
//
//                }
                ft.commit();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                findViewById(R.id.test).setTranslationX(drawerView.getWidth() * slideOffset);
                drawer.setScrimColor(Color.TRANSPARENT);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {

        if (manager.getBackStackEntryCount() > 1) {
            manager.popBackStack();

        }



            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void finis() {
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.after_login_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    public void finishh() {
        finish();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.contact_Infoo) {

            FragmentManager manager = AfterLoginScreen.this.getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ftt = manager.beginTransaction();
            ftt.add(R.id.RelativeLayoutHomeScreen, new ContactsInformation());
            ftt.addToBackStack("stackInfo");
//            try {
//                if (Global.statusFlag == true) {
//                    manager.popBackStack("stackStatus", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                }
//                if (Global.myProfileFlag == true) {
//                    manager.popBackStack("myProfile", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//
//                }
//
//                if (Global.addNewGroupFlag == true) {
//                    manager.popBackStack("addGroup", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                }
//                if (Global.friendProfile == true) {
//                    manager.popBackStack("friendProfile", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                }
//                if (Global.addFriendFlag == true) {
//                    manager.popBackStack("addFriend", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//
//                }
//                if (Global.searchGroupFlag == true) {
//                    manager.popBackStack("searchGroup", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//
//                }
//            } catch (Exception e) {
//
//            }
            ftt.commit();

        } else if (id == R.id.updateStatus) {

            FragmentManager manager = AfterLoginScreen.this.getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ftt1 = manager.beginTransaction();
            ftt1.add(R.id.RelativeLayoutHomeScreen, new StatusFragment());
            ftt1.addToBackStack("stackStatus");
//            try {
//                if (Global.infoFlag == true) {
//                    manager.popBackStack("stackInfo", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                }
//                if (Global.myProfileFlag == true) {
//                    manager.popBackStack("myProfile", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//
//                }
//                if (Global.addNewGroupFlag == true) {
//                    manager.popBackStack("addGroup", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                }
//                if (Global.friendProfile == true) {
//                    manager.popBackStack("friendProfile", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                }
//                if (Global.addFriendFlag == true) {
//                    manager.popBackStack("addFriend", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//
//                }
//                if (Global.searchGroupFlag == true) {
//                    manager.popBackStack("searchGroup", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//
//                }
//
//            } catch (Exception e) {
//
//            }
            ftt1.commit();

        } else if (id == R.id.addGroup) {
            FragmentManager manager = AfterLoginScreen.this.getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ftt = manager.beginTransaction();
            ftt.add(R.id.RelativeLayoutHomeScreen, new AddNewGroup());
            ftt.addToBackStack("addGroup");
//
//            try {
//                if (Global.infoFlag == true) {
//                    manager.popBackStack("stackInfo", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                }
//                if (Global.myProfileFlag == true) {
//                    manager.popBackStack("myProfile", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//
//                }
//                if (Global.statusFlag == true) {
//                    manager.popBackStack("stackStatus", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                }
//                if (Global.friendProfile == true) {
//                    manager.popBackStack("friendProfile", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                }
//                if (Global.addFriendFlag == true) {
//                    manager.popBackStack("addFriend", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//
//                }
//                if (Global.searchGroupFlag == true) {
//                    manager.popBackStack("searchGroup", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//
//                }
//            } catch (Exception e) {
//
//            }
            ftt.commit();

        } else if (id == R.id.mMyProfile) {

            FragmentManager manager = AfterLoginScreen.this.getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction ftt = manager.beginTransaction();
            ftt.add(R.id.RelativeLayoutHomeScreen, new MyProfile());
            ftt.addToBackStack("myProfile");

//            try {
//                if (Global.infoFlag == true) {
//                    manager.popBackStack("stackInfo", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                }
//                if (Global.statusFlag == true) {
//                    manager.popBackStack("stackStatus", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//
//                }
//                if (Global.addNewGroupFlag == true) {
//                    manager.popBackStack("addGroup", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                }
//                if (Global.friendProfile == true) {
//                    manager.popBackStack("friendProfile", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//                }
//                if (Global.addFriendFlag == true) {
//                    manager.popBackStack("addFriend", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//
//                }
//                if (Global.searchGroupFlag == true) {
//                    manager.popBackStack("searchGroup", FragmentManager.POP_BACK_STACK_INCLUSIVE);
//
//                }
//            } catch (Exception e) {
//
//            }
            ftt.commit();

        } else if (id == R.id.fb_logout_button) {
            Intent i = new Intent(AfterLoginScreen.this, Main2Activity.class);
            startActivity(i);

            LoginManager.getInstance().logOut();
            finis();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void transactionOfLoginScreen() {
        android.support.v4.app.FragmentTransaction ft2 = AfterLoginScreen.this.getSupportFragmentManager().beginTransaction();
        ft2.replace(R.id.RelativeLayoutHomeScreen, signInFragment);
        ft2.commit();

    }


    private void openPlaceCallActivity() {
//
    }

    @Override
    protected void onPause() {
        Log.d("nnn", "pause");
        Global.Afteractive = true;

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.d("nnn", "destroy");
        Global.Afteractive = false;

        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
        Global.Afteractive = true;
        Log.d("nnn", "start");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("nnn", "stop");

    }


}