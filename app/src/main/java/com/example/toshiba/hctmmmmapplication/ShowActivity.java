package com.example.toshiba.hctmmmmapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        Bundle bundle = getIntent().getExtras();
        boolean state = bundle.getBoolean("DEVICE_STATE");

        ImageView image = ( ImageView ) this.findViewById ( R.id.image );

        image.setImageResource ( );
    }
    }
}
