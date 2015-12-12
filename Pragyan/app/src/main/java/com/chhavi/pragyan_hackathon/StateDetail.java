package com.chhavi.pragyan_hackathon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toolbar;

/**
 * Created by chhavi on 12/12/15.
 */
public class StateDetail extends AppCompatActivity {

    private TextView name;
    private TextView capital;
    private TextView region;
    private TextView chiefMininster;
    private TextView language;


  //  String state = "Delhi";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.state_detail_layout);
       String statel  = "Delhi";
        statel = StatesPage.state;
      //  if(statel.equals("Delhi"))
        States state = States.find(States.class, "name = ?", statel).get(0);
   //     getActionBar().hide()
      //  this.getActionBar().hide();
        name = (TextView)findViewById(R.id.name_text);
        capital = (TextView)findViewById(R.id.capital_text);
        region = (TextView)findViewById(R.id.region_text);
        chiefMininster = (TextView)findViewById(R.id.chief_text);
        language = (TextView)findViewById(R.id.language_text);


        name.setText(state.name);
        capital.setText(state.capital);
        language.setText(state.main_language);
        region.setText(state.region);
        chiefMininster.setText(state.chief_minister);




    }
}
