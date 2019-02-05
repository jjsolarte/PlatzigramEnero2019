package co.jeisonsolarte.aplicandomaterialdesign.post.views;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import co.jeisonsolarte.aplicandomaterialdesign.R;
import co.jeisonsolarte.aplicandomaterialdesign.adapter.PictureAdapterRecycler;
import co.jeisonsolarte.aplicandomaterialdesign.model.CardviewPicturePOJO;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private static final int REQUEST_CAM = 1;
    private FloatingActionButton floatingBtn;
    private String photoPathTemp;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        showToolbar("Home", false, view);

        floatingBtn=view.findViewById(R.id.fragment_home_floating);

        RecyclerView recyclerView=view.findViewById(R.id.fragment_home_recycler);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        PictureAdapterRecycler pictureAdapter=new PictureAdapterRecycler(buildArray(),R.layout.cardview_picture,getActivity());
        recyclerView.setAdapter(pictureAdapter);

        floatingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

        return view;
    }

    private void takePicture() {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getActivity().getPackageManager())!=null){

            File foto=null;

            try {
                foto=crearImageFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (foto!=null){
                Uri fotoUri= FileProvider.getUriForFile(getActivity(),getActivity().getApplicationContext().getPackageName(),foto);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,fotoUri);
                startActivityForResult(intent,REQUEST_CAM);
            }
        }else {

        }
    }

    private File crearImageFile() throws IOException {

        String timeStamp=new SimpleDateFormat("yyMMdd_HH_mm_ss").format(new Date());
        String imageFile="JPEG_"+timeStamp+"_";
        File storageDir=getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        File photo=File.createTempFile(imageFile,".jpg",storageDir);

        photoPathTemp="file:"+photo.getAbsolutePath();

        return photo;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==REQUEST_CAM && resultCode==getActivity().RESULT_OK){
            Log.d("HomeFragment","ok bro :D");
            Intent intent=new Intent(getActivity(),NewPostActivity.class);
            intent.putExtra("PHOTO_PAHT_TEMP",photoPathTemp);
            startActivity(intent);
        }else {

        }
    }

    public ArrayList<CardviewPicturePOJO> buildArray(){
        ArrayList<CardviewPicturePOJO> picturePOJOS=new ArrayList<>();
        picturePOJOS.add(new CardviewPicturePOJO("https://www.coches.com/fotos_historicas/maserati/GranTurismo/high_80d4ba073cec11097fc0993d7c8fd174.jpg","User1","4 días","3"));
        picturePOJOS.add(new CardviewPicturePOJO("https://www.economiadigital.es/uploads/s1/61/27/65/2/tesla-model-3_15_970x597.jpeg","User2","5 días","2"));
        picturePOJOS.add(new CardviewPicturePOJO("https://www.diariomotor.com/imagenes/picscache/1920x1600c/ferrari-portofino-2017-002_1920x1600c.jpg","User3","6 días","5"));
        picturePOJOS.add(new CardviewPicturePOJO("https://www.economiadigital.es/uploads/s1/56/26/61/3/indoor-dark-1440_15_970x597.jpeg","User4","7 días","45"));
        return picturePOJOS;
    }

    public void showToolbar(String title,boolean upButton, View view){
        Toolbar toolbar = view.findViewById(R.id.my_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }
}
