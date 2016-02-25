package com.example.adnan.panachatfragment.Fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.adnan.panachatfragment.Activities.AfterLoginScreen;
import com.example.adnan.panachatfragment.Activities.Main2Activity;
import com.example.adnan.panachatfragment.Adaptors.PagerAdaptor;
import com.example.adnan.panachatfragment.BaseActivity;
import com.example.adnan.panachatfragment.R;
import com.example.adnan.panachatfragment.SinchService;
import com.example.adnan.panachatfragment.UTils.Global;
import com.example.adnan.panachatfragment.UTils.Utils;
import com.example.adnan.panachatfragment.Signatures.User;
import com.example.adnan.panachatfragment.Adaptors.adaptorUsers;
import com.example.adnan.panachatfragment.Signatures.signature_data;
import com.facebook.FacebookActivity;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.sinch.android.rtc.SinchError;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class signInFragment extends android.support.v4.app.Fragment implements SinchService.StartFailedListener, ServiceConnection {
    Bitmap bitmap;
    String ImgUrl;
    private SinchService.SinchServiceInterface mSinchServiceInterface;
    Firebase fire;
    android.support.v4.app.FragmentTransaction ft;
    CircularImageView imageview;
    Cloudinary cloudinary;
    View v;
    String url;
    String selectedImagePath;
    Picasso picasso;
    Profile profile;
    ProgressDialog dialog;

    public signInFragment() {
        // Required empty public constructor


    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        Firebase.setAndroidContext(this.getContext());
        FacebookSdk.sdkInitialize(getActivity());
        getActivity().bindService(new Intent(getActivity(), SinchService.class), this, getActivity().BIND_AUTO_CREATE);

        fire = new Firebase("https://saylanirrr.firebaseio.com/");
        cloudinary = Utils.cloudinary();


        v = inflater.inflate(R.layout.fragment_sign_in, container, false);
        picasso = Picasso.with(getActivity());
        profile = Profile.getCurrentProfile();

        profile();
        onClickImageView();
        getNavImage();
        if (profile != null) {
            Main2Activity.fa.finish();

        }
        ft = getActivity().getSupportFragmentManager().beginTransaction();
        ViewPager pager = (ViewPager) v.findViewById(R.id.view_pager);
        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tab_layout);

        android.support.v4.app.FragmentManager manager = getActivity().getSupportFragmentManager();
        int titles[] = {R.drawable.conversation, R.drawable.friends, R.drawable.group, R.drawable.bbbb};

        pager.setAdapter(new PagerAdaptor(manager, titles));
        tabLayout.setupWithViewPager(pager);
        tabLayout.getTabAt(0).setIcon(titles[0]);
        tabLayout.getTabAt(1).setIcon(titles[1]);
        tabLayout.getTabAt(2).setIcon(titles[2]);
        tabLayout.getTabAt(3).setIcon(titles[3]);

        return v;
    }


    public void profile() {

        dialog = new ProgressDialog(getActivity());
        dialog.setTitle("Loading...");
        dialog.setMessage("Fetching You Data Plz Wait");
        dialog.show();


        if (profile != null) {

            TextView text = (TextView) getActivity().findViewById(R.id.nameNav);
            text.setText(profile.getName());
            TextView text2 = (TextView) getActivity().findViewById(R.id.title);
            text2.setText(profile.getName());

            Global.uid = profile.getId();
            Global.name = profile.getName();
            fire.child("AppData").child("BasicInfo").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild(profile.getId())) {
                        dialog.dismiss();

                        get();


                    } else {

                        Toast.makeText(getActivity(), "Saving New User Data", Toast.LENGTH_SHORT).show();
                        Log.d("User Data Store", "New User Data Entry SAVING data");
                        fire.child("AppData").child("BasicInfo").child(profile.getId()).setValue(new User(profile.getName(), "N/A", "Available", "Not Available", "00000-0000", "Not Available", Global.uid));

                        Global.birthday = "Not Available";
                        Global.name = profile.getName();
                        Global.uid = profile.getId();
                        Global.picUrl = "N/A";
                        Global.contact = "00000-0000";
                        Global.status = "Available";
                        Global.email = "Not Available";
                        dialog.dismiss();
                    }

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
        }


    }

    public void onClickImageView() {
        imageview = (CircularImageView) getActivity().findViewById(R.id.imageView);
        imageview.setBorderWidth(0);
        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 0);
//
//            }
//        });


            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            try {
                Uri yy = data.getData();
                Log.d("dfdf", "picture");
                performCrop(yy);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {

            Bundle extras = data.getExtras();
            Uri img = data.getData();
            bitmap = extras.getParcelable("data");
            String path = saveImage(bitmap);
            selectedImagePath = path;
            Log.d("PATH", path);
            decodeFile(path);
            if (img == null) {

            } else {

            }
            super.onActivityResult(requestCode, resultCode, data);

        }
    }

    public void get() {

        fire.child("AppData").child("BasicInfo").child(profile.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                User u = dataSnapshot.getValue(User.class);
                Log.d("Ultimate", u.getName());
                Log.d("Ultimate", u.getEmail());

                Global.birthday = u.getBirthday();
                Global.name = u.getName();
                Global.picUrl = u.getPicUrl();
                Global.contact = u.getContact();
                Global.status = u.getStatus();
                Global.email = u.getEmail();
                dialog.dismiss();


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    private void decodeFile(String filePath) {
        Log.d("INVOKED", "Decode File");

        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, o);

        final int REQUIRED_SIZE = 1024;

        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;

        while (true) {
            if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        bitmap = BitmapFactory.decodeFile(filePath, o2);
        new AlertDialog.Builder(getActivity())
                .setTitle("Upload Picture")
                .setMessage("Are you sure you want to upload picture?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Bitmap oo = getRoundedShape(bitmap);
                        imageview.setImageBitmap(oo);
                        Log.d("File PATH IS ", selectedImagePath + "");
                        ////////////////////////////////UPLOADING CLOUDINARY////////////////////

                        AsyncTask<String, Void, HashMap<String, Object>> upload = new AsyncTask<String, Void, HashMap<String, Object>>() {
                            @Override
                            protected HashMap<String, Object> doInBackground(String... params) {
                                File file = new File(selectedImagePath);
                                HashMap<String, Object> responseFromServer = null;
                                try {
                                    responseFromServer = (HashMap<String, Object>) cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
                                } catch (IOException e) {
                                    Toast.makeText(getActivity(), "Cannot Upload Image Please Try Again", Toast.LENGTH_LONG).show();
                                    e.printStackTrace();
                                }

                                return responseFromServer;
                            }

                            @Override
                            protected void onPostExecute(HashMap<String, Object> stringObjectHashMap) {
                                url = (String) stringObjectHashMap.get("url");
//                                fire.child("AppData").child("Picture").child(Global.uid).child("PicUrl").setValue(url);
                                Global.picUrl = url;
                                Log.d("HHH", Global.status);
                                Log.d("HHH", Global.email);
                                fire.child("AppData").child("BasicInfo").child(Global.uid).setValue(new User(Global.name, url, Global.status, Global.email, Global.contact, Global.birthday, Global.uid), new Firebase.CompletionListener() {
                                    @Override
                                    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                                        Snackbar.make(imageview, "Picture Updated", Snackbar.LENGTH_LONG)
                                                .setAction("Action", null).show();

                                    }
                                });

//

                            }
                        };
                        upload.execute(selectedImagePath);
                        ////////////////////////////////UPLOADING COMPLETED////////////////////////////////////////
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                }).show();
    }


    private String saveImage(Bitmap finalBitmap) {
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/Elips/ProfileImages");

        if (!myDir.exists())
            myDir.mkdirs();

        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image_" + n + ".jpg";
        File file = new File(myDir, fname);

        if (file.exists())
            file.delete();

        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return root + "/Elips/ProfileImages/" + fname;
    }


    private void performCrop(Uri picUri) {
        try {
            //Start Crop Activity
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            // indicate image type and Uri
            cropIntent.setDataAndType(picUri, "image/*");
            // set crop properties
            cropIntent.putExtra("crop", "true");
            // indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            // indicate output X and Y
            cropIntent.putExtra("outputX", 128);
            cropIntent.putExtra("outputY", 128);
            // retrieve data on return
            cropIntent.putExtra("return-data", true);
            // start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, 2);
        } catch (Exception e) {

        }
    }


    public void getNavImage() {
        fire.child("AppData").child("BasicInfo").child(Global.uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
//                    Toast.makeText(getActivity(),Global.uid,Toast.LENGTH_SHORT).show();
                    ImgUrl = dataSnapshot.child("picUrl").getValue().toString();
                    loadBitmap(ImgUrl);
                    Global.picUrl = ImgUrl;
                    Log.d("Tag", "" + ImgUrl);
                } catch (Exception e) {

                }
            }

            //            }
//
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
        // TODO Auto-generated method stub
        int targetWidth = 200;
        int targetHeight = 200;
        Log.d("Tago", "get rounded shape");

        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                targetHeight, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidth - 1) / 2,
                ((float) targetHeight - 1) / 2,
                (Math.min(((float) targetWidth),
                        ((float) targetHeight)) / 2),
                Path.Direction.CCW);

        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
//paint.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setFilterBitmap(true);
        canvas.drawOval(new RectF(0, 0, targetWidth, targetHeight), paint);
//paint.setColor(Color.TRANSPARENT);

        canvas.clipPath(path);
        Bitmap sourceBitmap = scaleBitmapImage;
        canvas.drawBitmap(sourceBitmap,
                new Rect(0, 0, sourceBitmap.getWidth(),
                        sourceBitmap.getHeight()),
                new Rect(0, 0, targetWidth,
                        targetHeight), null);
        return targetBitmap;
    }
//                .into(imageView);

    private Target loadtarget;


    public void loadBitmap(String url) {

        if (loadtarget == null) loadtarget = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                // do something with the Bitmap
                handleLoadedBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable drawable) {

            }

            @Override
            public void onPrepareLoad(Drawable drawable) {

            }


        };

        Picasso.with(getActivity()).load(url).into(loadtarget);
    }


    public void handleLoadedBitmap(Bitmap b) {
        // do something here
        Bitmap round = getRoundedShape(b);
        Log.d("Tago", "handle loded bitmap");
        Global.myRoundedPic = round;
        imageview.setImageBitmap(round);
    }


    @Override
    public void onStarted() {
//        Toast.makeText(this, "Service started mainActivity", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStartFailed(SinchError error) {
//        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
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
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {

//        Toast.makeText(getActivity(),"onResumeSignIn",Toast.LENGTH_SHORT).show();

        ImageButton grp = (ImageButton) getActivity().findViewById(R.id.searchGroups);
        grp.setVisibility(View.VISIBLE);
        TextView text2 = (TextView) getActivity().findViewById(R.id.title);
        text2.setText(Global.name);
        ImageButton add = (ImageButton) getActivity().findViewById(R.id.addFriendButton);
        add.setVisibility(View.VISIBLE);

        super.onStart();


    }

}





