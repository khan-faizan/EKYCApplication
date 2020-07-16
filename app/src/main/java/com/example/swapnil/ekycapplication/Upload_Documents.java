package com.example.swapnil.ekycapplication;


import android.content.Context;
import android.content.Intent;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class Upload_Documents extends AppCompatActivity {
    Button NextBtn;
    LinearLayout emiratesFront,emiratesbackll,signaturell;
    private ImageView emiratesfront,emiratesbackImg,signatureImg;
    private Uri filePath;
    String imageIdentifier,eidstring;
    private final int PICK_IMAGE_REQUEST = 22;
    StorageReference storageReffront,storageRefback,storageRefsignature;
    ProgressBar progressBar;

    //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload__documents);


        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if(CheckInternet.isNetworkAvailable(connectivityManager)){
            emiratesfront=(ImageView) findViewById(R.id.emiratedidfront);
            emiratesbackImg=(ImageView) findViewById(R.id.emiratesidback);
            signatureImg=(ImageView) findViewById(R.id.signupload);

            emiratesFront=(LinearLayout)findViewById(R.id.emiratesidfrontll);
            emiratesbackll=(LinearLayout)findViewById(R.id.emiratesidbackll);
            signaturell=(LinearLayout)findViewById(R.id.signaturell);
            NextBtn=(Button)findViewById(R.id.gototsummary);
            eidstring=getIntent().getExtras().getString("eidnumber");
            progressBar=(ProgressBar)findViewById(R.id.predictprogress);

            progressBar.setProgress(0);
            // Create a storage reference from our app
            storageReffront = FirebaseStorage.getInstance().getReference(eidstring+"frontimage");
            storageRefback = FirebaseStorage.getInstance().getReference(eidstring+"backimage");
            storageRefsignature = FirebaseStorage.getInstance().getReference(eidstring+"signatureimage");

            emiratesbackll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageIdentifier="emiratesback";
                    SelectImage();
                }
            });

            signaturell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageIdentifier="signature";
                    SelectImage();
                }
            });

            emiratesFront.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageIdentifier="emiratesfront";
                    SelectImage();
                }
            });

            NextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    uploadImage();
                }
            });
        }else{
            Intent gotologinscreen=new Intent(Upload_Documents.this,NoInternet.class);
            startActivity(gotologinscreen);
        }


    }

    private void SelectImage()
    {

        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

    // Override onActivityResult method
    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data)
    {

        super.onActivityResult(requestCode,
                resultCode,
                data);

        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);

                if(imageIdentifier.equals("emiratesfront")){
                    emiratesfront.setImageBitmap(bitmap);
                }else if(imageIdentifier.equals("emiratesback")){
                    emiratesbackImg.setImageBitmap(bitmap);
                }else{
                    signatureImg.setImageBitmap(bitmap);
                }

            }

            catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }

    private void uploadImage()
    {
        progressBar.setProgress(10);
        //emiratesfront upload
        emiratesfront.setDrawingCacheEnabled(true);
        emiratesfront.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) emiratesfront.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();


        UploadTask uploadTask = storageReffront.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(Upload_Documents.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressBar.setProgress(20);
                Toast.makeText(Upload_Documents.this, "Emirates Front Side Uploaded", Toast.LENGTH_SHORT).show();

            }
        });

        //emiratesback upload

        emiratesbackImg.setDrawingCacheEnabled(true);
        emiratesbackImg.buildDrawingCache();
        Bitmap bitmapback = ((BitmapDrawable) emiratesbackImg.getDrawable()).getBitmap();
        ByteArrayOutputStream baosback = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baosback);
        byte[] databack = baosback.toByteArray();


        UploadTask uploadTaskback = storageRefback.putBytes(data);
        uploadTaskback.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(Upload_Documents.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressBar.setProgress(30);
                Toast.makeText(Upload_Documents.this, "Emirates Back side uploaded successfully", Toast.LENGTH_SHORT).show();

            }
        });

        //sign upload

        signatureImg.setDrawingCacheEnabled(true);
        signatureImg.buildDrawingCache();
        Bitmap bitmapsignature = ((BitmapDrawable) signatureImg.getDrawable()).getBitmap();
        ByteArrayOutputStream baossignature = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baossignature);
        byte[] databacksignature = baossignature.toByteArray();


        UploadTask uploadTasksignature = storageRefsignature.putBytes(data);
        uploadTasksignature.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(Upload_Documents.this, exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Upload_Documents.this, "Signature Uploaded successfully", Toast.LENGTH_SHORT).show();
                Intent confirmationIntent=new Intent(Upload_Documents.this,Success.class);
                startActivity(confirmationIntent);
            }
        });

        uploadTasksignature.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                int value=(int)progress;
                if(value>30) {
                    progressBar.setProgress(value);
                }
            }
        });

    }
}
