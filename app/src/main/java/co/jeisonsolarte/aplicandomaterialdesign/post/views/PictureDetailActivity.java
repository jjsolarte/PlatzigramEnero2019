package co.jeisonsolarte.aplicandomaterialdesign.post.views;

import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import co.jeisonsolarte.aplicandomaterialdesign.PlatzigramAplication;
import co.jeisonsolarte.aplicandomaterialdesign.R;

public class PictureDetailActivity extends AppCompatActivity {

    private static final String PHOTO_NAME = "JPEG_190211_18_34_49_6306990691976497639.jpg";
    ImageView imageView;
    PlatzigramAplication app;
    StorageReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_detail);

        app= (PlatzigramAplication) getApplicationContext();
        reference=app.getStorageReference();

        imageView=findViewById(R.id.picture_detail_img);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setEnterTransition(new Fade());
        }
        showToolbar(true);

        showData();
    }

    private void showData() {
        reference.child("postImage/"+PHOTO_NAME)
                .getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(imageView);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
                Toast.makeText(PictureDetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                FirebaseCrash.report(e);
            }
        });

    }

    public void showToolbar(boolean btnBack){
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(btnBack);
        CollapsingToolbarLayout collapsingToolbar=findViewById(R.id.picture_detail_collapsing);
    }
}
