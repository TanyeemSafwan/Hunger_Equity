package com.example.myapplication.hunger_equity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.hunger_equity.model.CharityModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class CharityHomePic extends AppCompatActivity {

    private Button upload,cancel;
    private ImageView choose;
    private ProgressBar progress;
    private DatabaseReference mainD= FirebaseDatabase.getInstance().getReference("Pics");
    private StorageReference refer= FirebaseStorage.getInstance().getReference();
    private Uri imageUri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_home_pic);
        upload=(Button) findViewById(R.id.charity_upload);
        cancel=(Button)findViewById(R.id.charity_upload_cancel);
        progress=(ProgressBar) findViewById(R.id.progress_charity);
        choose=(ImageView) findViewById(R.id.charity_user_photo_upload);

        progress.setVisibility(View.INVISIBLE);

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gIntent=new Intent();
                gIntent.setAction(Intent.ACTION_GET_CONTENT);
                gIntent.setType("image/*");
                startActivityForResult(gIntent,2);

            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageUri != null){
                    uploadToFirebase(imageUri);
                }else{
                    Toast.makeText(CharityHomePic.this, "Please Select Image", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharityHomePic.this.finish();
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode ==2 && resultCode == RESULT_OK && data != null){

            imageUri = data.getData();
            choose.setImageURI(imageUri);

        }
    }
    private void uploadToFirebase(Uri uri){
        Intent in=getIntent();
        String userName=in.getStringExtra("galleryName");
        final StorageReference fileRef = refer.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        CharityModel model = new CharityModel(uri.toString());
                        mainD.child(userName).setValue(model);
                        progress.setVisibility(View.INVISIBLE);
                        Toast.makeText(CharityHomePic.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                        NextActivity();
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                progress.setVisibility(View.VISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progress.setVisibility(View.INVISIBLE);
                Toast.makeText(CharityHomePic.this, "Uploading Failed !!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private String getFileExtension(Uri mUri){

        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));

    }
    public void NextActivity()
    {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                CharityHomePic.this.finish();
            }
        }, 1300);

    }

}