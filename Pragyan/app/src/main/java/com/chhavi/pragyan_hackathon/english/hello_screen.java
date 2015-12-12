package com.chhavi.pragyan_hackathon.english;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chhavi.pragyan_hackathon.R;

import java.util.Locale;

/**
 * Created by chhavi on 12/12/15.
 */
public class hello_screen extends AppCompatActivity implements
        TextToSpeech.OnInitListener {
    EditText take_name;
    Button submit_button;
    public static String name;
    String textToSpeak;
    TextToSpeech textToSpeech;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_name_layout);
        submit_button = (Button)findViewById(R.id.submit_button);
        textToSpeech = new TextToSpeech(getApplicationContext(), this);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                take_name = (EditText)findViewById(R.id.name_input);
                if(!take_name.getText().equals("")){
                    name = take_name.getText().toString();
                    textToSpeak = "Hello and welcome " + name;
                    convertTextToSpeech(textToSpeak);
                    Thread timer = new Thread() {
                        public void run(){
                            try {
                               // buttons[inew][jnew].setBackgroundColor(Color.BLACK);
                                sleep(5000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    };
                    timer.start();
                    Intent i = new Intent(hello_screen.this, TeachName.class);
                    startActivity(i);



                }
            }
        });

    }
    private void convertTextToSpeech(String text) {
      //  String text = inputText.getText().toString();
        if (null == text || "".equals(text)) {
            text = "Please give some input.";
        }
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = textToSpeech.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("error", "This Language is not supported");
            } else {
                convertTextToSpeech(textToSpeak);
            }
        } else {
            Log.e("error", "Initilization Failed!");
        }
    }
}
