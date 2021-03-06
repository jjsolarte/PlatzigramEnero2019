package co.jeisonsolarte.aplicandomaterialdesign.post.views;


import android.Manifest;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import co.jeisonsolarte.aplicandomaterialdesign.R;
import co.jeisonsolarte.aplicandomaterialdesign.adapter.PictureAdapterRecycler;
import co.jeisonsolarte.aplicandomaterialdesign.model.CardviewPicturePOJO;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    private static final int REQUEST_CAM = 1;
    private FloatingActionButton floatingBtn;
    private String photoPathTemp="";

    private final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL=0;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_home, container, false);
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
                if (ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL);

                }else {

                    takePicture();

                }
            }
        });

        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    takePicture();

                } else {
                    Toast.makeText(getContext(), "Se requiere el permiso para continuar", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void takePicture() {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION|Intent.FLAG_GRANT_READ_URI_PERMISSION);
        if (intent.resolveActivity(getActivity().getPackageManager())!=null){

            File foto=null;
            Uri fotoUri=null;
            try {
                foto=crearImageFile();
                fotoUri= FileProvider.getUriForFile(getContext(),getActivity().getApplicationContext().getPackageName(),foto);
            } catch (Exception e) {
                e.printStackTrace();
                FirebaseCrash.report(e);
            }
            if (foto!=null){
                intent.putExtra(MediaStore.EXTRA_OUTPUT,fotoUri);
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        intent.setClipData(ClipData.newRawUri("",fotoUri));
                    }
                    intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION|Intent.FLAG_GRANT_READ_URI_PERMISSION);
                }
                startActivityForResult(intent,REQUEST_CAM);
            }
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
