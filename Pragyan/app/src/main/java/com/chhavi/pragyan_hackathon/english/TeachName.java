package com.chhavi.pragyan_hackathon.english;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.chhavi.pragyan_hackathon.R;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by chhavi on 12/12/15.
 */
public class TeachName extends AppCompatActivity implements
        TextToSpeech.OnInitListener{
    private TextView displayName;
    private ImageButton speakButton;
    private ImageButton recordButton;
    private String result_text;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    String check;
    TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teach_name);
        displayName = (TextView)findViewById(R.id.text_display);
        check = "My name is "+ hello_screen.name;
        displayName.setText(check);
        textToSpeech = new TextToSpeech(getApplicationContext(), this);
        speakButton = (ImageButton)findViewById(R.id.speak_button);
        recordButton =(ImageButton)findViewById(R.id.micbutton);
        speakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTextToSpeech(check);
            }
        });
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });

    }
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Say Something");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Speech not supported",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    displayName.setText(result.get(0));
                    result_text = result.get(0);
                    if (check.equalsIgnoreCase(result_text)) {
                        Toast.makeText(TeachName.this, "Hurray!", Toast.LENGTH_LONG).show();
                    }
                    break;
                }

            }
        }
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
              //  convertTextToSpeech(check);
            }
        } else {
            Log.e("error", "Initilization Failed!");
        }
    }
}
