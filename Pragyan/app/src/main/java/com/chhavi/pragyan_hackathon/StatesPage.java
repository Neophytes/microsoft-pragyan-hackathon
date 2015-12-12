package com.chhavi.pragyan_hackathon;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by chhavi on 12/12/15.
 */
public class StatesPage extends AppCompatActivity {
    private TextView txtSpeechInput;
    private ImageButton btnSpeak;
    public static String state;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.states_activity_layout);

       // States banglore = new States()


        List<States> states = new ArrayList<>();
        states.add(new States("Delhi","Capital: New Delhi", "Location: North Region", "Chief Minister: Arvind Kejriwal", "Language(s): Hindi , English"));
        states.add(new States("Karnataka", "Capital: Bengaluru", "Location: South India", "Chief Minister: Siddaramaiah", "Language(s): Kannada"));
        states.add(new States("Maharashtra", "Capital: Mumbai", "Location: Western India", "Chief Minister: Devendra Fadnavis", "Language(s): Marathi"));
        states.add(new States("Punjab", "Capital: Chandigarh", "Location: Northern India", "Chief Minister: Prakash Singh Badal", "Language(s): Punjabi"));
        states.add(new States("Assam", "Capital: Dispur", "Location: Eastern India", "hief Minister: Tarun Gogoi", "Language(s): Assamese"));



      //  states.add(new States("Title here 3", "4nd edition"));
        SugarRecord.saveInTx(states);

        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
      //  spinner.setOnItemSelectedListener(this);

        final List<String> categories = new ArrayList<String>();
        categories.add("asfasfaskjf asijfasfljaslkj asijoaskjlddaskjl");
        categories.add("Delhi");
        categories.add("Karnataka");
        categories.add("Maharashtra");
        categories.add("Punjab");
        categories.add("Assam");
      //  categories.add("Travel");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        state = "";
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                state = categories.get(position);
                if (state.equalsIgnoreCase("Delhi") || state.equalsIgnoreCase("Maharashtra") || state.equalsIgnoreCase("Assam") || state.equalsIgnoreCase("Punjab") || state.equalsIgnoreCase("Karnataka")) {
                    Intent i = new Intent(StatesPage.this, StateDetail.class);
                    startActivity(i);
                } else {
                   // Toast.makeText(StatesPage.this, "Invalid State, Please Try Again", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });

        txtSpeechInput = (TextView) findViewById(R.id.txtSpeechInput);
        btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);

        // hide the action bar
       // getActionBar().hide();

        btnSpeak.setOnClickListener(new View.OnClickListener() {

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
                    txtSpeechInput.setText(result.get(0));
                    state = result.get(0);
                    if(state.equalsIgnoreCase("Delhi")||state.equalsIgnoreCase("Maharashtra")||state.equalsIgnoreCase("Assam")||state.equalsIgnoreCase("Punjab")||state.equalsIgnoreCase("Karnataka")) {
                        Intent i = new Intent(StatesPage.this, StateDetail.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(StatesPage.this, "Invalid State, Please Try Again",Toast.LENGTH_LONG).show();
                    }
                }
                break;
            }

        }
    }

    /**
     * Handle the action of the button being clicked
     */
   /* public void speakButtonClicked(View v)
    {
        startVoiceRecognitionActivity();
    }
*/
    /**
     * Fire an intent to start the voice recognition activity.
     */
/*    private void startVoiceRecognitionActivity()
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Voice recognition Demo...");
        startActivityForResult(intent, REQUEST_CODE);
    }*/

    /**
     * Handle the results from the voice recognition activity.
     */

}

