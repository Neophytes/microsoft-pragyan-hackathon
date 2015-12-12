package com.chhavi.pragyan_hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by chhavi on 12/12/15.
 */
public class ChooseAvatar extends AppCompatActivity{
    private ImageButton girlAvatar;
    private ImageButton boyAvatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_avatar);
        girlAvatar = (ImageButton)findViewById(R.id.girlbutton);
        boyAvatar = (ImageButton)findViewById(R.id.boybutton);

        girlAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChooseAvatar.this, HomeGrid.class);
                startActivity(i);
            }
        });
        boyAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChooseAvatar.this, HomeGrid.class);
                startActivity(i);
            }
        });
    }
}
