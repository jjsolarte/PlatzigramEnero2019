package co.jeisonsolarte.aplicandomaterialdesign.views.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import co.jeisonsolarte.aplicandomaterialdesign.R;
import co.jeisonsolarte.aplicandomaterialdesign.adapter.PictureAdapterRecycler;
import co.jeisonsolarte.aplicandomaterialdesign.model.CardviewPicturePOJO;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_search, container, false);

        RecyclerView recyclerView=view.findViewById(R.id.fragment_search_recycler);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //recyclerView.setLayoutManager(layoutManager);

        PictureAdapterRecycler pictureAdapter=new PictureAdapterRecycler(buildArray(),R.layout.cardview_picture,getActivity());
        recyclerView.setAdapter(pictureAdapter);

        return view;
    }

    public ArrayList<CardviewPicturePOJO> buildArray(){
        ArrayList<CardviewPicturePOJO> picturePOJOS=new ArrayList<>();
        picturePOJOS.add(new CardviewPicturePOJO("https://www.coches.com/fotos_historicas/maserati/GranTurismo/high_80d4ba073cec11097fc0993d7c8fd174.jpg","User1","4 días","3"));
        picturePOJOS.add(new CardviewPicturePOJO("https://www.economiadigital.es/uploads/s1/61/27/65/2/tesla-model-3_15_970x597.jpeg","User2","5 días","2"));
        picturePOJOS.add(new CardviewPicturePOJO("https://www.diariomotor.com/imagenes/picscache/1920x1600c/ferrari-portofino-2017-002_1920x1600c.jpg","User3","6 días","5"));
        picturePOJOS.add(new CardviewPicturePOJO("https://www.economiadigital.es/uploads/s1/56/26/61/3/indoor-dark-1440_15_970x597.jpeg","User4","7 días","45"));
        return picturePOJOS;
    }


}
