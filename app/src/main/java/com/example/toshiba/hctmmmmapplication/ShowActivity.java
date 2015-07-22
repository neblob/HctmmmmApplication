package com.example.toshiba.hctmmmmapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.io.IOException;

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
        try {
            image.setImageBitmap(prepareImage(capturedImageUri));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void getIntentFromOtherActivity() {
        Intent intent = getIntent();
        capturedImageUri = intent.getStringExtra(Helper.CAPTURED_IMAGE_URL);
    }

    private Bitmap prepareImage(String uri) throws IOException {
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(uri));;
        //rotate bitmap
        Matrix matrix = new Matrix();
        matrix.postRotate(getExifOrientation(uri));
        //create new rotated bitmap
        return Bitmap.createBitmap(bitmap, 0, 0,bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private int getExifOrientation(String filepath) {
        int degree = 0;
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(filepath);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (exif != null) {
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
            if (orientation != -1) {
                // We only recognise a subset of orientation tag values.
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        degree = 90;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        degree = 180;
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        degree = 270;
                        break;
                }

            }
        }

        return degree;
    }
}

