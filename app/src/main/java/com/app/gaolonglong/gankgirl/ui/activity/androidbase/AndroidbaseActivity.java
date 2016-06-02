package com.app.gaolonglong.gankgirl.ui.activity.androidbase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.app.gaolonglong.gankgirl.R;
import com.app.gaolonglong.gankgirl.ui.activity.androidbase.opensourcelib.OpenSourceLibActivity;
import com.app.gaolonglong.gankgirl.ui.activity.androidbase.view_anim.snow_view.SnowActivity;
import com.app.gaolonglong.gankgirl.ui.activity.gank.MainActivity;
import com.beardedhen.androidbootstrap.BootstrapButton;

public class AndroidbaseActivity extends AppCompatActivity {

    BootstrapButton androidbase_comprehensive_button;
    BootstrapButton androidbase_viewandanim_button;
    BootstrapButton androidbase_opensourcelib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_androidbase);

        androidbase_comprehensive_button = (BootstrapButton)findViewById(R.id.androidbase_comprehensive_button);
        androidbase_comprehensive_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AndroidbaseActivity.this, MainActivity.class));
            }
        });

        androidbase_viewandanim_button = (BootstrapButton)findViewById(R.id.androidbase_viewandanim_button);
        androidbase_viewandanim_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AndroidbaseActivity.this, SnowActivity.class));
            }
        });

        androidbase_opensourcelib = (BootstrapButton)findViewById(R.id.androidbase_opensource_button);
        androidbase_opensourcelib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AndroidbaseActivity.this, OpenSourceLibActivity.class));
            }
        });
    }
}
