package co.jeisonsolarte.aplicandomaterialdesign.post.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import co.jeisonsolarte.aplicandomaterialdesign.R;

public class NewPostActivity extends AppCompatActivity {

    ImageView imgFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        imgFoto=findViewById(R.id.newpost_img);
        if (getIntent().getExtras()!=null){
            String photoPath=getIntent().getExtras().getString("PHOTO_PATH_TEMP");
            Picasso.get().load(photoPath).into(imgFoto);
        }
    }

}
