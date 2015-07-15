package com.example.toshiba.hctmmmmapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CameraActivity extends Activity {

    private static final int TAKE_PICTURE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        initInstance();
    }

    private void initInstance() {
        initBtnCapture();
        initCameraSurface();
    }

    private void initCameraSurface() {
        // TODO: 7/15/2015 AD complete serface view
    }

    private void initBtnCapture() {
        Button btnCapture = (Button)findViewById(R.id.shutter_button);
        btnCapture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShowActivity.class);
//                intent.putExtra("DEVICE_STATE",);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        ImageView image = (ImageView) findViewById(R.id.imageView);

        if (requestCode == TAKE_PICTURE && resultCode == RESULT_OK) {
            Bitmap captureImage = (Bitmap)
                    data.getExtras().get("data");
            image.setImageBitmap(captureImage);
        }

    }

}
