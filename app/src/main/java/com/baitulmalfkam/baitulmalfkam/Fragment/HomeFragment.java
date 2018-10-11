package com.baitulmalfkam.baitulmalfkam.Fragment;


import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.baitulmalfkam.baitulmalfkam.Adapter.ItemAdapter;
import com.baitulmalfkam.baitulmalfkam.DonasiActivity;
import com.baitulmalfkam.baitulmalfkam.POJO.ListItem;
import com.baitulmalfkam.baitulmalfkam.R;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    RecyclerView rv;
    ProgressBar progressBar;
    ItemAdapter adapter;
    NestedScrollView nested;
    View shadow;
    SliderLayout slider_image;
    FloatingActionButton floating_buttton;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        //floating_buttton = rootView.findViewById(R.id.floating_buttton);

        progressBar = rootView.findViewById(R.id.progress_bar);
        shadow = rootView.findViewById(R.id.shadow);
        floating_buttton = rootView.findViewById(R.id.floating_buttton);

        slider_image = rootView.findViewById(R.id.slider_image);
        nested = rootView.findViewById(R.id.nested);
        slider_image.setIndicatorAnimation(SliderLayout.Animations.SCALE_DOWN);
        slider_image.setScrollTimeInSec(2);

        setSliderViews();



        floating_buttton.setVisibility(View.GONE);
        floating_buttton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent donasiInten = new Intent(getContext(), DonasiActivity.class);
                getContext().startActivity(donasiInten);


            }
        });

        rv = rootView.findViewById(R.id.rv_content);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        

        new loadUrlHome(this).execute();
        return rootView;
    }

    private void setSliderViews() {

        for (int i = 0; i <= 3; i++) {

            SliderView sliderView = new SliderView(getContext());

            switch (i) {
                case 0:
                    sliderView.setImageUrl("http://baitulmalfkam.com/slider1");
                    break;
                case 1:
                    sliderView.setImageUrl("http://baitulmalfkam.com/slider2");
                    break;
                case 2:
                    sliderView.setImageUrl("http://baitulmalfkam.com/slider3");
                    break;
                case 3:
                    sliderView.setImageUrl("http://baitulmalfkam.com/slider4");
                    break;
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
            sliderView.setDescription("setDescription " + (i + 1));
            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    Toast.makeText(getActivity(), "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();
                }
            });

            //at last add this view in your layout :
            slider_image.addSliderView(sliderView);
        }

    }

    private class loadUrlHome extends AsyncTask<String, Integer, ArrayList> {
        private WeakReference<HomeFragment> activityReference;

        public loadUrlHome(HomeFragment context) {
            activityReference = new WeakReference<>(context);
        }


        @Override
        protected void onPreExecute() {
            //textView.setText("Hello !!!");
            progressBar.setVisibility(View.VISIBLE);
            slider_image.setVisibility(View.INVISIBLE);
            shadow.setVisibility(View.INVISIBLE);
            super.onPreExecute();
        }

        @Override
        protected ArrayList doInBackground(String... strings) {
            SyncHttpClient client = new SyncHttpClient();
            String url_home = "http://baitulmalfkam.com/api/get_recent_posts/";
            final ArrayList<ListItem> dataList = new ArrayList<>();

            client.get(url_home, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    Log.d("Membaca URL","OnSucces : BERHASIL");
                    try {
                        JSONObject object = new JSONObject(new String(responseBody));
                        JSONArray posts = object.getJSONArray("posts");
                        dataList.clear();

                        for (int i =0; i < posts.length(); i++){
                            JSONObject itempost = posts.getJSONObject(i);
                            String id = itempost.getString("id");
                            String url = itempost.getString("url");
                            String title = itempost.getString("title");
                            String content = itempost.getString("content");
                            String date = itempost.getString("date");
                            String thumbnail = itempost.getString("thumbnail");


                            ListItem listItem = new ListItem(id,url,title,content,date,thumbnail);
                            dataList.add(listItem);
                            
                        }




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
            return dataList;
        }

        protected void onPostExecute(ArrayList dataList){
            progressBar.setVisibility(View.INVISIBLE);
            slider_image.setVisibility(View.VISIBLE);
            shadow.setVisibility(View.VISIBLE);
            floating_buttton.setVisibility(View.VISIBLE);
            adapter = new ItemAdapter(dataList, getContext());
            final Animation scaleUp = AnimationUtils.loadAnimation(getActivity(),R.anim.scale_up);
            final Animation scaleDown = AnimationUtils.loadAnimation(getActivity(),R.anim.scale_down);
            rv.setAdapter(adapter);

            rv.setNestedScrollingEnabled(false);



            /*nested.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                if(v.getChildAt(v.getChildCount() - 1) != null) {
                //    if (scrollY > oldScrollY) {
                  //      floating_buttton.animate().translationY(floating_buttton.getHeight() + 15).setInterpolator(new LinearInterpolator()).start();
                   // }
                    //if (scrollY < oldScrollY) {
                      //  floating_buttton.animate().translationY(0).setInterpolator(new LinearInterpolator()).start();
                    //}

                    if (scrollY == 0) {
                        floating_buttton.animate().translationY(floating_buttton.getHeight() + 15).setInterpolator(new LinearInterpolator()).start();

                    }

                    if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                        floating_buttton.animate().translationY(0).setInterpolator(new LinearInterpolator()).start();

                    }
                }
            });*/

           rv.addOnScrollListener(new RecyclerView.OnScrollListener() {

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (dy > 0 && floating_buttton.getVisibility() == View.VISIBLE) {
                        //floating_buttton.startAnimation(scaleDown);
                        floating_buttton.setVisibility(View.GONE);
                        floating_buttton.animate().translationY(floating_buttton.getHeight() + 15).setInterpolator(new LinearInterpolator()).start();


                    } else if (dy < 0 && floating_buttton.getVisibility() != View.VISIBLE) {
                        //floating_buttton.startAnimation(scaleUp);
                        floating_buttton.setVisibility(View.VISIBLE);
                        floating_buttton.animate().translationY(0).setInterpolator(new LinearInterpolator()).start();

                    }
                }
            });
            super.onPostExecute(dataList);
        }


    }
}
