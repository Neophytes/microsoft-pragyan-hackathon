package com.chhavi.pragyan_hackathon.world;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chhavi.pragyan_hackathon.R;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by chhavi on 12/12/15.
 */
public class WorldMap extends AppCompatActivity{
    ImageView mImageView;
    PhotoViewAttacher mAttacher;
    TextView worldText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.world_layout);
        // Any implementation of ImageView can be used!
        mImageView = (ImageView) findViewById(R.id.world_map);
        worldText = (TextView)findViewById(R.id.world_text);

        // Set the Drawable displayed
        Drawable bitmap = getResources().getDrawable(R.drawable.world_map);
        mImageView.setImageDrawable(bitmap);



        // Attach a PhotoViewAttacher, which takes care of all of the zooming functionality.
        mAttacher = new PhotoViewAttacher(mImageView);
        mAttacher.setOnPhotoTapListener(new PhotoTapListener());
        mAttacher.setScale(2, true);
    }
    private class PhotoTapListener implements PhotoViewAttacher.OnPhotoTapListener {

        @Override
        public void onPhotoTap(View view, float x, float y) {
            float xPercentage = x * 100f;
            float yPercentage = y * 100f;
            Drawable bitmap = getResources().getDrawable(R.drawable.world_india);
            mImageView.setImageDrawable(bitmap);
            mAttacher.update();
            mAttacher.setScale(2, true);
            worldText.setText("You have reached India. नमस्ते| ");
          //  showToast(String.format(PHOTO_TAP_TOAST_STRING, xPercentage, yPercentage, view == null ? 0 : view.getId()));
        }
    }
}
