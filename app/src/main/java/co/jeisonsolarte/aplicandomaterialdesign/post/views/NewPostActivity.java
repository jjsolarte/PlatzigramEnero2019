package co.jeisonsolarte.aplicandomaterialdesign.post.views;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.crash.component.FirebaseCrashRegistrar;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

import co.jeisonsolarte.aplicandomaterialdesign.PlatzigramAplication;
import co.jeisonsolarte.aplicandomaterialdesign.R;

public class NewPostActivity extends AppCompatActivity {

    ImageView imgFoto;
    String photoPath="";

    PlatzigramAplication app;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        imgFoto=findViewById(R.id.newpost_img);
        if (getIntent().getExtras()!=null){
            photoPath=getIntent().getExtras().getString("PHOTO_PAHT_TEMP");
            showPhoto();
        }

        app= (PlatzigramAplication) getApplicationContext();
        storageReference=app.getStorageReference();
    }

    private void showPhoto(){
        Picasso.get().load(photoPath).into(imgFoto);
    }

    public void newPost(View view){
        imgFoto.setDrawingCacheEnabled(true);
        imgFoto.buildDrawingCache();

        Bitmap bitmap=imgFoto.getDrawingCache();
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);

        byte[] photoByte = baos.toByteArray();

        String photoName=photoPath.substring(photoPath.lastIndexOf("/")+1,photoPath.length());

        final StorageReference photoReference=storageReference.child("postImage/"+photoName);

        final UploadTask uploadTask=photoReference.putBytes(photoByte);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                FirebaseCrash.report(e);
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            //sorry por el uri
                            Log.w("sorry","no se subió... :(");
                        }

                        // Continue with the task to get the download URL
                        return photoReference.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()){
                            Uri downloadUri = task.getResult();
                            String photoURL = downloadUri.toString();
                            Log.w("NewPostFailTask", "URL Photo > " + photoURL);
                            finish();
                        }else {
                            Log.w("sorry2","no se subió... :(");
                        }
                    }
                });
            }
        });
    }

}
