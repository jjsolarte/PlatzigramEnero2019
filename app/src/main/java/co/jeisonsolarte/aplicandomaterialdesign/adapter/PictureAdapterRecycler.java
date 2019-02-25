package co.jeisonsolarte.aplicandomaterialdesign.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import co.jeisonsolarte.aplicandomaterialdesign.R;
import co.jeisonsolarte.aplicandomaterialdesign.model.CardviewPicturePOJO;
import co.jeisonsolarte.aplicandomaterialdesign.post.views.PictureDetailActivity;

public class PictureAdapterRecycler extends RecyclerView.Adapter<PictureAdapterRecycler.PictureViewHolder>{

    private ArrayList<CardviewPicturePOJO> arrayList;
    private int resources;
    private Activity activity;

    public PictureAdapterRecycler(ArrayList<CardviewPicturePOJO> arrayList, int resources, Activity activity) {
        this.arrayList = arrayList;
        this.resources = resources;
        this.activity = activity;
    }

    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(resources,viewGroup,false);
        return new PictureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PictureViewHolder pictureViewHolder, int i) {
        CardviewPicturePOJO picturePOJO=arrayList.get(i);
        pictureViewHolder.txtUsername.setText(picturePOJO.getUsername());
        pictureViewHolder.txtTime.setText(picturePOJO.getTime());
        pictureViewHolder.txtLike2.setText(picturePOJO.getLikeNumbre());
        Picasso.get().load(picturePOJO.getImg()).into(pictureViewHolder.imgCard);


        pictureViewHolder.imgCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, PictureDetailActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    Explode explode=new Explode();
                    explode.setDuration(500);
                    activity.getWindow().setExitTransition(explode);
                    activity.startActivity(intent,
                            ActivityOptionsCompat.makeSceneTransitionAnimation(
                                    activity,v,activity.getString(R.string.cardview_img_transition
                                    )).toBundle());
                }else {
                    activity.startActivity(intent);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class PictureViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgCard;
        private TextView txtUsername;
        private TextView txtFirstword;
        private TextView txtTime;
        private TextView txtLike2;


        public PictureViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCard=itemView.findViewById(R.id.cardview_img);
            txtUsername=itemView.findViewById(R.id.cardview_username);
            txtFirstword=itemView.findViewById(R.id.cardview_first_word);
            txtTime=itemView.findViewById(R.id.cardview_time);
            txtLike2=itemView.findViewById(R.id.cardview_like2);
        }
    }
}
