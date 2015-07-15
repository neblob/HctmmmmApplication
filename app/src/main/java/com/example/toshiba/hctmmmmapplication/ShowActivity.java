package com.example.toshiba.hctmmmmapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class ShowActivity extends AppCompatActivity {

    String capturedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        getIntentFromOtherActivity();

        initInstance();
    }

    private void initInstance() {
        ImageView image = (ImageView) this.findViewById(R.id.ivCapturedImage);
        image.setImageURI(Uri.parse(capturedImageUri));
    }

    private void getIntentFromOtherActivity() {
        Intent intent = getIntent();
        capturedImageUri = intent.getStringExtra(Helper.CAPTURED_IMAGE_URL);
    }
}

