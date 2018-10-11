package com.baitulmalfkam.baitulmalfkam.Program;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.baitulmalfkam.baitulmalfkam.R;

public class AAQ extends AppCompatActivity {
    ImageView iv_program;
    TextView tv_program, title_content;
    Button btn_donasi;
    CollapsingToolbarLayout main_collapsing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aaq);

        Intent intent = getIntent();
        Bundle kirim = intent.getExtras();

        String namap = kirim.getString("namap");
        String detail = kirim.getString("lengkap");
        int gambar = kirim.getInt("gambar");



        main_collapsing = findViewById(R.id.main_collapsing);
        iv_program= findViewById(R.id.iv_program);
        tv_program = findViewById(R.id.tv_program);
        title_content = findViewById(R.id.title_content);
        btn_donasi = findViewById(R.id.btn_donasi);

        main_collapsing.setExpandedTitleColor(getResources().getColor(R.color.transparan));
        main_collapsing.setCollapsedTitleTextColor(getResources().getColor(R.color.colorPrimary));
        main_collapsing.setBackgroundColor(getResources().getColor(R.color.white));
        main_collapsing.setTitle(namap);

        iv_program.setImageResource(gambar);
        tv_program.setText(Html.fromHtml(detail));
        title_content.setText(namap);
    }
}
