package com.chhavi.pragyan_hackathon.internet;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.chhavi.pragyan_hackathon.R;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

/**
 * Created by chhavi on 12/12/15.
 */
public class SearchExplain extends AppCompatActivity {
    ShowcaseView showcaseView;
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_explain);
        showcaseView = new ShowcaseView.Builder(this)
                .withNewStyleShowcase()
                .setTarget(new ViewTarget(R.id.search_view, this))
                .setContentTitle("Search Bar")
                .setContentText("Type text to search here\n\nयहाँ खोज करने के लिए संदेश लिखें")//.setContentTitlePaint(titlePaint)
                .setStyle(R.style.CustomShowcaseTheme2)
                .build();

    flag = 0;
        final Button startButton = (Button)findViewById(R.id.button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == 0) {
                    showcaseView = new ShowcaseView.Builder(SearchExplain.this)
                            .withNewStyleShowcase()
                            .setTarget(new ViewTarget(R.id.press_button, SearchExplain.this))
                            .setContentTitle("Search Button")
                            .setContentText("Press Button to Search\n\nखोज करने के लिए बटन दबाएँ")
                            .setStyle(R.style.CustomShowcaseTheme2)
                            .build();
                    showcaseView.show();
                    // startButton.setText("Continue");
                    //  showcaseView.show();
                    flag = 1;
                    // showcaseView.setShowcase(new ViewTarget(body),true);
                }else if(flag == 1){
                    showcaseView = new ShowcaseView.Builder(SearchExplain.this)
                            .withNewStyleShowcase()
                            .setTarget(new ViewTarget(R.id.results, SearchExplain.this))
                            .setContentTitle("Results")
                            .setContentText("View your results here\n\nयहाँ अपने परिणाम देखें")
                            .setStyle(R.style.CustomShowcaseTheme2)
                            .build();
                    showcaseView.show();
                    flag = 2;
                }else{
                    startButton.setText("Try it!");
                    Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.co.in"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setPackage("com.android.chrome");
                    try {
                        SearchExplain.this.startActivity(intent);
                    } catch (ActivityNotFoundException ex) {
                        // Chrome browser presumably not installed so allow user to choose instead
                        intent.setPackage(null);
                        SearchExplain.this.startActivity(intent);
                    }
                  //  Intent i = new Intent(SearchExplain.this, SearchExplain.class);
                    //startActivity(i);
                }

            }
        });
    }
}
