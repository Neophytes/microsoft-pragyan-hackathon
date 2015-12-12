package com.chhavi.pragyan_hackathon.world;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.chhavi.pragyan_hackathon.R;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by chhavi on 12/12/15.
 */
public class WorldMap extends AppCompatActivity{
    ImageView mImageView;
    PhotoViewAttacher mAttacher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.world_layout);
        // Any implementation of ImageView can be used!
        mImageView = (ImageView) findViewById(R.id.world_map);

        // Set the Drawable displayed
        Drawable bitmap = getResources().getDrawable(R.drawable.world_map);
        mImageView.setImageDrawable(bitmap);

        // Attach a PhotoViewAttacher, which takes care of all of the zooming functionality.
        mAttacher = new PhotoViewAttacher(mImageView);
    }
}
