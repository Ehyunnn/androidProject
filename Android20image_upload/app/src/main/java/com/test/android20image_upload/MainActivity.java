package com.test.android20image_upload;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final int REQUEST_CODE_READ_EXTERNAL_STORAGE = 111;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_READ_EXTERNAL_STORAGE);
        }

        imageView = (ImageView) findViewById(R.id.ImageView01);

        Button getImage = (Button) findViewById(R.id.getImage);
        getImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(intent, 1);

            }
        });


        Button sendMultipart = (Button) findViewById(R.id.sendMultipart);
        sendMultipart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        doProcess();
                    }
                }.start();

            }
        });



    }//end onCreate()

    private Handler mHandler = new Handler();
    private String insert_result;

    private void doProcess() {
        Log.i("Main Log", "doProcess()..getAbsolutePath.."+ Environment.getExternalStorageDirectory().getAbsolutePath());
        Log.i("Main Log", "doProcess()...getAbsoluteFile.."+ Environment.getExternalStorageDirectory().getAbsoluteFile());

        String charset = "UTF-8";
//        File uploadFile1 = new File(Environment.getExternalStorageDirectory(),"DCIM/Camera/IMG_20150331_053729.jpg");
//        File uploadFile1 = new File("/storage/emulated/0/Android/data/com.android.browser/files/Download/"+filePath);
        File uploadFile1 = new File(filePath);
        String requestURL = "http://192.168.0.163:8090/web16FileUpload/uploadOk.jsp";


        Log.i("Main Log", "doProcess()...uploadFile1.exists():"+uploadFile1.exists());

        try {
            MultipartUtility multipart = null;
            try {
                multipart = new MultipartUtility(requestURL, charset);
                Log.i("Main Log", "doProcess()...multipart:"+multipart);

                for (File f:ContextCompat.getExternalFilesDirs(this,fileName)) {
                    Log.i("Main Log", "doProcess()...getExternalFilesDirs:");
                    Log.i("Main Log", f.toString() );
                }

            } catch (IOException e) {
                e.printStackTrace();
                Log.i("Main Log", "doProcess()...IOException:" + e.toString());
            }



            multipart.addHeaderField("User-Agent", "CodeJava");
            multipart.addHeaderField("Test-Header", "Header-Value");

            multipart.addFormField("tel", "010-0000-0000");
            multipart.addFilePart("multipartFile", uploadFile1);
            multipart.finish();

            List<String> response = multipart.finish();

            Log.i("Main Log", "SERVER REPLIED:");

            for (String line : response) {
                Log.i("Main Log", line);
            }
        } catch (IOException ex) {
            Log.i("Main Log", ex.toString());
        }
    }



    Uri curImgURI;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        curImgURI = data.getData();
        Log.i("Main Log", ">>>" + curImgURI.toString());
        Log.i("Main Log", ">>>" + curImgURI.getPath().toString());

        setImage(curImgURI);
    }


    String filePath;
    String fileName;

    private void setImage(Uri curImgURI) {
        Log.i("Main Log", ">>>" + curImgURI.toString());

        /////filePath
        Cursor cursor = getContentResolver().query(curImgURI, null, null, null, null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            for (int i=0;i<cursor.getColumnCount();i++){
                Log.i("Main Log", "Column >>>" + cursor.getColumnName(i));
                Log.i("Main Log", ">>>" + cursor.getString(cursor.getColumnIndexOrThrow(cursor.getColumnName(i))));
            }
            filePath = cursor.getString(cursor.getColumnIndex("_data"));
//            fileName = cursor.getString(cursor.getColumnIndex("bucket_display_name"));
            fileName = "/"+cursor.getString(cursor.getColumnIndex("_display_name"));
            Log.i("Main Log", "fileName >>>" +fileName);
                    cursor.moveToNext();
        }


        Log.i("Main Log", "filePath >>>" + filePath);

        cursor.close();

        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(curImgURI), null, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(bitmap);


    }



}//end class
