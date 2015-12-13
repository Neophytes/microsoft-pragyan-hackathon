package com.chhavi.pragyan_hackathon;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntroFragment;

/**
 * Created by chhavi on 13/12/15.
 */
public class AppIntroMine extends com.github.paolorotolo.appintro.AppIntro {
    @Override
    public void init(Bundle savedInstanceState) {
        // Add your slide's fragments here
        // AppIntro will automatically generate the dots indicator and buttons.
       /* addSlide(first_fragment);
        addSlide(second_fragment);
        addSlide(third_fragment);
        addSlide(fourth_fragment);*/
        setBarColor(getResources().getColor(R.color.colorPrimary));

        //  com.github.paolorotolo.appintro.AppIntro.showSkipButton(true);
        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest
        addSlide(AppIntroFragment.newInstance("Welcome !", getResources().getString(R.string.homedesc), R.drawable.rsz_ss2, getResources().getColor(R.color.colorPrimaryDark)));
        addSlide(AppIntroFragment.newInstance("Don't let him hang !", getResources().getString(R.string.hangmandesc), R.drawable.ss3, getResources().getColor(R.color.black)));
        addSlide(AppIntroFragment.newInstance("Speak and Learn !", getResources().getString(R.string.spklrn), R.drawable.ss1, getResources().getColor(R.color.colorPrimaryDark)));
        addSlide(AppIntroFragment.newInstance("Follow me !", getResources().getString(R.string.insdesc), R.drawable.ss4, getResources().getColor(R.color.lavender)));

        // OPTIONAL METHODS
        // Override bar/separator color
        //  setBarColor(Color.parseColor("#3F51B5"));
        // setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button
       showSkipButton(true);
        showDoneButton(true);

        // Turn vibration on and set intensity
        // NOTE: you will probably need to ask VIBRATE permesssion in Manifest
       // setVibrate(true);
        //setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed() {

    }

    @Override
    public void onDonePressed() {
        Intent i = new Intent(AppIntroMine.this, ChooseAvatar.class);
        startActivity(i);

    }

    @Override
    public void onNextPressed() {

    }

    @Override
    public void onSlideChanged() {

    }
}


