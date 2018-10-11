package com.baitulmalfkam.baitulmalfkam.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.baitulmalfkam.baitulmalfkam.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LayananFragment extends Fragment {

    ImageView quran, kalkulator, hisnul, adzan;


    public LayananFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_layanan, container, false);
        quran = rootView.findViewById(R.id.quran);
        kalkulator = rootView.findViewById(R.id.kalkulator);
        hisnul = rootView.findViewById(R.id.hisnul);
        adzan = rootView.findViewById(R.id.adzan);

        quran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Qur'an", Toast.LENGTH_LONG).show();
            }
        });

        return rootView;
    }

}
