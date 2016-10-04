package com.example.faiz.imageprac;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final int Browse_image = 1;
    private static final int SELECT_DOC_DIALOG = 1;
    static final int PICK_FILE_REQUEST = 101;
    private String imgDecodableString;

    private File imageFile;
    private Bitmap bitmap;
    private Uri selectedImageUri;
    ImageView image;
    private String selectedImagePath;

    private TextView linkss;

    private File temp_path;
    private final int COMPRESS = 100;

    private Long lenght;
    private FirebaseStorage storage;
    private StorageReference storageRef, folderRef, imageRef;
    private UploadTask mUploadTask;
    private String imageName;
    private RelativeLayout vieww;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Utils.setCloudinary();
        storage = FirebaseStorage.getInstance();

        vieww = (RelativeLayout)findViewById(R.id.main);
        storageRef = storage.getReference();

        folderRef = storageRef.child("gs://firestorage-9f08f.appspot.com/");


        linkss = (TextView) findViewById(R.id.linkss);
        image = (ImageView) findViewById(R.id.imageview);
        Button btn = (Button) findViewById(R.id.button);
        Button btnpdf = (Button) findViewById(R.id.btnpdf);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("*/image*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), Browse_image);

            }
        });

        btnpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
                intent.setType("*/*");
                String[] mimetypes = {"application/pdf/", "application/msword/"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes);
                startActivityForResult(intent, PICK_FILE_REQUEST);
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == this.RESULT_OK) {
            if (requestCode == Browse_image || requestCode == SELECT_DOC_DIALOG) {

//                if (Build.VERSION.SDK_INT < 19) {
//                    Uri selectedImage = data.getData();
//
//                    String[] filePDFpath = {MediaStore.Files.FileColumns.MEDIA_TYPE};
//
//                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
//                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
//                    cursor.moveToFirst();
//                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                    selectedImagePath = cursor.getString(columnIndex);
//                    cursor.close();
//                    System.out.println("smallImagePath" + selectedImagePath);
//                    Log.d("TagSmall", selectedImagePath);
//
//                    image.setImageBitmap(BitmapFactory.decodeFile(selectedImagePath));
//
//                } else {
//                    try {
//                        InputStream imInputStream = getContentResolver().openInputStream(data.getData());
//
//                        Bitmap bitmap = BitmapFactory.decodeStream(imInputStream);
//
//                        selectedImagePath = saveGalaryImageOnLitkat(bitmap);
//                        image.setImageBitmap(BitmapFactory.decodeFile(selectedImagePath));
//
//                        Log.d("TagLarge", selectedImagePath);
//
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//                }
//
//                uploadImage();
//
//            }
        //    else if(resultCode == SELECT_DOC_DIALOG){
                Uri selectedDoc = data.getData();
                File fileRef = new File(selectedDoc.getPath());
                Log.d("filesize",String .valueOf(fileRef.getTotalSpace()));
//long length = file.length();
                lenght = fileRef.length();
                lenght = lenght/1024;

                System.out.println("File Path : " + fileRef.getPath() + ", File size : " + lenght +" KB");
                Log.d("uridata",selectedDoc.getPath());

                final long FIVE_MEGABYTE = 1024 * 1024 * 5;

                lenght = lenght*1024;

                if(lenght<=FIVE_MEGABYTE) {

                    Uri file = Uri.fromFile(new File(selectedDoc.getPath()));
                    StorageReference imageRef = folderRef.child(file.getLastPathSegment());
                    mUploadTask = imageRef.putFile(file);
                    mUploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            Log.d("DownloadURL", downloadUrl.toString());
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            System.out.println("Upload is " + progress + "% done");

                        }
                    }).addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {
                            System.out.println("Upload is paused");

                        }
                    });
                }else{
                   Toast.makeText(MainActivity.this,"File size is too biG?",Toast.LENGTH_LONG).show();

                }
            }
        }
    }

    public void uploadImage(){

        image.setDrawingCacheEnabled(true);
        image.buildDrawingCache();
        Bitmap bitmap = image.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data1 = baos.toByteArray();

        imageRef = folderRef.child(imageName);

        mUploadTask = imageRef.putBytes(data1);
        mUploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

                // mTextView.setText(String.format("Failure: %s", exception.getMessage()));
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.

                Log.d("ImageURl",taskSnapshot.getDownloadUrl().toString());
                linkss.setText(taskSnapshot.getDownloadUrl().toString());

                //  mTextView.setText(taskSnapshot.getDownloadUrl().toString());
            }
        });

    }


    private String saveGalaryImageOnLitkat(Bitmap bitmap) {
        try {
            File cacheDir;
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
                cacheDir = new File(Environment.getExternalStorageDirectory(), getResources().getString(R.string.app_name));
            else
                cacheDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            if (!cacheDir.exists())
                cacheDir.mkdirs();
            String filename = System.currentTimeMillis() + ".jpg";
            File file = new File(cacheDir, filename);
          Log.d("imageName",file.getName());
            imageName = file.getName();
            temp_path = file.getAbsoluteFile();
            // if(!file.exists())
            file.createNewFile();
            FileOutputStream out = new FileOutputStream(file);
          //  Log.d("fileLenght",String.valueOf(file.length()/1024));
            bitmap.compress(Bitmap.CompressFormat.JPEG, COMPRESS, out);

            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    private void showFileChooser() {

        Intent intentPDF = new Intent(Intent.ACTION_GET_CONTENT);
        intentPDF.setType("application/pdf");
        intentPDF.addCategory(Intent.CATEGORY_OPENABLE);

        Intent intentTxt = new Intent(Intent.ACTION_GET_CONTENT);
        intentTxt.setType("text/plain");
        intentTxt.addCategory(Intent.CATEGORY_OPENABLE);

        Intent intentXls = new Intent(Intent.ACTION_GET_CONTENT);
        intentXls.setType("application/x-excel");
        intentXls.addCategory(Intent.CATEGORY_OPENABLE);

        PackageManager packageManager = getPackageManager();

        List activitiesPDF = packageManager.queryIntentActivities(intentPDF,
                PackageManager.MATCH_DEFAULT_ONLY);
        boolean isIntentSafePDF = activitiesPDF.size() > 0;

        List activitiesTxt = packageManager.queryIntentActivities(intentTxt,
                PackageManager.MATCH_DEFAULT_ONLY);
        boolean isIntentSafeTxt = activitiesTxt.size() > 0;

        List activitiesXls = packageManager.queryIntentActivities(intentXls,
                PackageManager.MATCH_DEFAULT_ONLY);
        boolean isIntentSafeXls = activitiesXls.size() > 0;

        if (!isIntentSafePDF || !isIntentSafeTxt || !isIntentSafeXls){

            // Potentially direct the user to the Market with a Dialog

        }else {
            Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
        }

    }
}