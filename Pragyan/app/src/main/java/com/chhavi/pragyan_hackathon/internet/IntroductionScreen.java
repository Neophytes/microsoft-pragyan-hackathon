package com.chhavi.pragyan_hackathon.internet;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.chhavi.pragyan_hackathon.R;

/**
 * Created by chhavi on 12/12/15.
 */
public class IntroductionScreen extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.internet_introduction_layout);
        ScaleAnimation scaleAnim1 = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f);
        scaleAnim1.setDuration(800);
        scaleAnim1.setStartOffset(500);
        scaleAnim1.setFillAfter(true);
        //TextView justSpecial = (TextView)findViewById(R.id.just_special_text);
        ImageView justSpecial = (ImageView)findViewById(R.id.tut_image);
        justSpecial.setVisibility(View.VISIBLE);
        justSpecial.setAnimation(scaleAnim1);
    }
}
