package com.chhavi.pragyan_hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.chhavi.pragyan_hackathon.english.hello_screen;
import com.chhavi.pragyan_hackathon.internet.IntroductionScreen;

/**
 * Created by chhavi on 12/12/15.
 */
public class HomeGrid extends AppCompatActivity{
        private ImageButton indiaOption;
    private ImageButton worldOption;
    private ImageButton internetOption;
    private ImageButton englishOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_grid);
        indiaOption = (ImageButton)findViewById(R.id.indiaOption);
        worldOption = (ImageButton)findViewById(R.id.worldOption);
        internetOption = (ImageButton)findViewById(R.id.internetOption);
        englishOption = (ImageButton)findViewById(R.id.englishOption);


        indiaOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeGrid.this, StatesPage.class);
                startActivity(i);
            }
        });
        englishOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeGrid.this, hello_screen.class);
                startActivity(i);

            }
        });
        internetOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeGrid.this, IntroductionScreen.class);
                startActivity(i);

            }
        });
    }
}
