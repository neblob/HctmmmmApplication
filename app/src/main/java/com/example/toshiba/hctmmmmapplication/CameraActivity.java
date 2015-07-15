package com.example.toshiba.hctmmmmapplication;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        initInstance();
    }

    private void initInstance() {
        initBtnCapture();
    }

    private void initBtnCapture() {
        Button btnCapture = (Button) findViewById(R.id.shutter_button);
        btnCapture.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                MySurfaceView.takePicture(new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] bytes, Camera camera) {
                        try {
                            File imageFile = createImageFile();
                            FileOutputStream fos = new FileOutputStream(imageFile);
                            fos.write(bytes);
                            fos.flush();
                            fos.close();

                            goToShowActivity(imageFile.toURI().toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }

    private void goToShowActivity(String uri) {
        Intent intent = new Intent(CameraActivity.this, ShowActivity.class);
        intent.putExtra(Helper.CAPTURED_IMAGE_URL, uri);
        startActivity(intent);
    }

    @NonNull
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);

        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }

}
