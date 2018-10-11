package com.baitulmalfkam.baitulmalfkam.Program;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.baitulmalfkam.baitulmalfkam.R;

public class Yatim extends AppCompatActivity {
    ImageView iv_program;
    TextView tv_program;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yatim);

        iv_program = findViewById(R.id.iv_program);
        tv_program = findViewById(R.id.tv_program);

        String yatim = getString(R.string.yatim_smart);
        tv_program.setText(Html.fromHtml(yatim));
    }
}
