package com.baitulmalfkam.baitulmalfkam.Fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Toast;

import com.baitulmalfkam.baitulmalfkam.Adapter.ProgramAdapter;
import com.baitulmalfkam.baitulmalfkam.AutoFitGridLayoutManager;
import com.baitulmalfkam.baitulmalfkam.POJO.Program;
import com.baitulmalfkam.baitulmalfkam.Program.AAQ;
import com.baitulmalfkam.baitulmalfkam.Program.Dhuafa;
import com.baitulmalfkam.baitulmalfkam.Program.Mandiri;
import com.baitulmalfkam.baitulmalfkam.Program.Medical;
import com.baitulmalfkam.baitulmalfkam.Program.SAR;
import com.baitulmalfkam.baitulmalfkam.Program.Yatim;
import com.baitulmalfkam.baitulmalfkam.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DonasiFragment extends Fragment implements ProgramAdapter.Itemlistener {
    RecyclerView rv_program;


    public DonasiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_donasi, container, false);

        rv_program = rootView.findViewById(R.id.rv_program);

        ArrayList<Program> program = new ArrayList<>();
        program.add(new Program("Akademi AlQur'an FKAM",R.drawable.aaq, getString(R.string.dakwah_pendidikan)));
        program.add(new Program("Dhuafa Smart", R.drawable.dhuafa_smart, getString(R.string.dhuafa_smart)));
        program.add(new Program("Muslim Medical",R.drawable.muslim_medical,getString(R.string.muslim_medical)));
        program.add(new Program("Yatim Smart",R.drawable.yatim_smart, getString(R.string.yatim_smart)));
        program.add(new Program("SAR FKAM",R.drawable.sar, getString(R.string.tanggap_bencana)));
        program.add(new Program("Sentra Kemandirian Insani",R.drawable.ic_share, getString(R.string.sarana_ibadah)));

        ProgramAdapter adapter = new ProgramAdapter(getContext(), program,this);
        rv_program.setAdapter(adapter);

        /**
         AutoFitGridLayoutManager that auto fits the cells by the column width defined.
         **/

        AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(getContext(), 250);
        rv_program.setLayoutManager(layoutManager);

       // GridLayoutManager manager = new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL, false);
        //rv_program.setLayoutManager(manager);

        return rootView;
    }

    @Override
    public void onItemClick(Program program) {

        int image = program.gambarProgram;
        String detail = program.detailprogram;
        String nama = program.namaProgram;

        Intent detailIntent = new Intent(getActivity(),AAQ.class);
        Bundle kirim = new Bundle();

        kirim.putInt("gambar", image);
        kirim.putString("lengkap",detail);
        kirim.putString("namap",nama);

        detailIntent.putExtras(kirim);
        detailIntent.setType("image/*");
        //getActivity().startActivity(detailIntent);
    }

   /* private void setSingleEvent(final GridLayout mainGrid) {

        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView

            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    if (finalI == 0) {
                        Intent intent = new Intent(getActivity(), Dhuafa.class);
                        startActivity(intent);

                    } else if (finalI == 1) {
                        Intent intent = new Intent(getActivity(), Yatim.class);
                        startActivity(intent);
                    } else if (finalI == 2) {
                        Intent intent = new Intent(getActivity(), Medical.class);
                        startActivity(intent);
                    }else if (finalI == 3){
                        Intent intent = new Intent(getActivity(), AAQ.class);
                        startActivity(intent);

                    }else if (finalI == 4){
                        Intent intent = new Intent(getActivity(), SAR.class);
                        startActivity(intent);
                    }else if (finalI == 5){
                        Intent intent = new Intent(getActivity(), Mandiri.class);
                        startActivity(intent);


                    }
                }

                });
            };
        }*/
    }

