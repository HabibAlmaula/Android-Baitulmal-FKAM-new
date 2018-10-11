package com.baitulmalfkam.baitulmalfkam.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baitulmalfkam.baitulmalfkam.Detail.DetailMajalahActivity;
import com.baitulmalfkam.baitulmalfkam.POJO.Majalah;
import com.baitulmalfkam.baitulmalfkam.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MajalahAdapter extends RecyclerView.Adapter <MajalahAdapter.MajalahViewHolder> {
    ArrayList<Majalah> dataMajalah;
    Context context;

    public MajalahAdapter (ArrayList<Majalah>dataMajalah, Context context){
        this.dataMajalah = dataMajalah;
        this.context = context;
    }

    //membuat viewholder
    @Override
    public MajalahAdapter.MajalahViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View tampilkan = LayoutInflater.from(parent.getContext()).inflate(R.layout.majalah,parent,false);

        return new MajalahViewHolder(tampilkan);
    }


    //menangkap viewholder
    @Override
    public void onBindViewHolder(MajalahAdapter.MajalahViewHolder holder, int position) {
        final Majalah majalah = dataMajalah.get(position);

        holder.tv_title.setText(majalah.getTitle());
        holder.tv_date.setText(majalah.getEdisi());
        Picasso.with(context).load(majalah.getThumbnail()).into(holder.iv_Majalah);

        holder.iv_Majalah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindahBaru = new Intent(context,DetailMajalahActivity.class);
                pindahBaru.putExtra("MAJALAH",majalah);
                context.startActivity(pindahBaru);
            }
        });

        holder.tombolShare.setOnClickListener(new View.OnClickListener() {
            String klik = "Yuk Baca Majalah Online Baitulmal FKAM! \n \n";
            String digital = "http://baitulmalfkam.com/media/majalah-digital/";
            String edisi = majalah.getEdisi().toString().trim();
            String judul = majalah.getTitle().toString().trim();
            String url = majalah.getUrl().toString().trim();

            @Override
            public void onClick(View v) {
                Picasso.with(context).load(majalah.getThumbnail()).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

                        //perizinan share gambar
                        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                        StrictMode.setVmPolicy(builder.build());

                        Intent intent = new Intent(Intent.ACTION_SEND);
                        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                        intent.putExtra(Intent.EXTRA_TEXT,klik  + edisi +"\n" +"*"+ judul +"*"+"\n"+ url);
                        intent.putExtra(Intent.EXTRA_STREAM, getLocalBitmapUri (bitmap));
                        intent.setType("image/*");
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        context.startActivity(Intent.createChooser(intent,"Share Via:"));

                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });


            }
        });
    }

    public Uri getLocalBitmapUri(Bitmap bmp) {
        Uri bmpUri = null;
        try {
            File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bmpUri;
    }

    @Override
    public int getItemCount() {
        return dataMajalah.size();
    }

    public class MajalahViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_date;
        ImageView iv_Majalah;
        FloatingActionButton tombolShare;

        public MajalahViewHolder(View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title);
            tv_date = itemView.findViewById(R.id.tv_date);
            iv_Majalah = itemView.findViewById(R.id.iv_majalah);
            tombolShare = itemView.findViewById(R.id.btn_share);


        }
    }

}
