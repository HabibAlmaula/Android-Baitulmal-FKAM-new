package com.baitulmalfkam.baitulmalfkam.Program;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.baitulmalfkam.baitulmalfkam.R;

public class Dhuafa extends AppCompatActivity {
    ImageView iv_program;
    TextView tv_program;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dhuafa);

        iv_program = findViewById(R.id.iv_program);
        tv_program = findViewById(R.id.tv_program);

        String dhuafa = getString(R.string.dhuafa_smart);

        tv_program.setText(Html.fromHtml(dhuafa));
    }
}
