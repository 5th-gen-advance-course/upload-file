package com.example.ratha.homework07_upload_iamge;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.ratha.homework07_upload_iamge.entity.response.UrlIconResponse;
import com.example.ratha.homework07_upload_iamge.repository.Services;
import com.example.ratha.homework07_upload_iamge.repository.network.ServiceGenerator;
import com.example.ratha.homework07_upload_iamge.util.MultiPartBodyCreator;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private ProgressBar progressBar;
    private static final int PICK_IMAGE=1;
    private String filepath;
    Services.FileUploadService uploadService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.ImagePicker);
        progressBar=findViewById(R.id.progressBar);
        progressBar.setVisibility(ProgressBar.GONE);

        createServices();
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                    new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE },
                    PICK_IMAGE);
        }
    }

    private void createServices() {
        uploadService= ServiceGenerator.CreateService(Services.FileUploadService.class);
    }

    public void onPickImage(View view) {

        Intent intent=new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent,PICK_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==PICK_IMAGE){
            if(resultCode==MainActivity.RESULT_OK){
                if(null!=data){
                    Uri uri=data.getData();
                    try{
                        String[] filePathColumn={MediaStore.Images.Media.DATA};
                        Cursor cursor =getContentResolver().query(
                                uri,filePathColumn,null,null,null
                        );
                        cursor.moveToFirst();
                        int columnIndex=cursor.getColumnIndex(filePathColumn[0]);
                        filepath=cursor.getString(columnIndex);
                        cursor.close();
                        Bitmap bitmap= BitmapFactory.decodeFile(filepath);
                        imageView.setImageBitmap(bitmap);
                        upload(filepath);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }else{
                    return;
                }
            }

        }
    }

    private void upload(String filepath) {
        Uri uri = Uri.parse(filepath);
        Log.e("file->",filepath);
        MultipartBody.Part part= MultiPartBodyCreator.createPart(this,"file",uri);
        Call<UrlIconResponse> call =uploadService.uploadUrlIcon(part);
        progressBar.setVisibility(ProgressBar.VISIBLE);
        call.enqueue(new Callback<UrlIconResponse>() {
            @Override
            public void onResponse(Call<UrlIconResponse> call, Response<UrlIconResponse> response) {
                try {
                    Log.e("sms-> ", response.body().getMsg());
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    progressBar.setVisibility(ProgressBar.GONE);
                }

            }

            @Override
            public void onFailure(Call<UrlIconResponse> call, Throwable t) {
                Log.e("error-> ",t.toString());
                progressBar.setVisibility(ProgressBar.VISIBLE);
            }
        });
    }


}
