package com.gfirem.testofflinepicasa;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class MainActivity extends AppCompatActivity {

    private ImageView img;
    private ProgressBar progressBar;
    private TextView txtFrom;
    private Button btnRetry;
    private Picasso picasso;
    private String source = "http://dev.magic-xperience.com/sites/default/files/menu_icons/menu_icon_2075.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView) findViewById(R.id.imageView);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        btnRetry = (Button) findViewById(R.id.btnRetry);
        txtFrom = (TextView) findViewById(R.id.textView);
        txtFrom.setText("Wait for load image...");

        picasso = Picasso.with(this);
        picasso.setLoggingEnabled(true);

        picasso.load(source).into(target);

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (picasso != null)
                    picasso.load(source).into(target);
            }
        });


    }

    Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            progressBar.setVisibility(View.GONE);
            img.setImageBitmap(bitmap);
            txtFrom.setText("Loaded image from " + Picasso.LoadedFrom.values()[from.ordinal()]);
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
            progressBar.setVisibility(View.GONE);
            txtFrom.setText("Error getting image");
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
