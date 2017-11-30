package com.example.ratha.homework07_upload_iamge.repository;

import com.example.ratha.homework07_upload_iamge.entity.response.UrlIconResponse;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by ratha on 11/30/2017.
 */

public interface Services {

    interface FileUploadService{

        @Multipart
        @POST("/api/v1/urls/upload/web-icon")
        Call<UrlIconResponse> uploadUrlIcon(@Part MultipartBody.Part file);

    }
}
