package com.chhavi.pragyan_hackathon.internet;

import android.app.ExpandableListActivity;
import android.content.res.Resources;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chhavi.pragyan_hackathon.R;
import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.ViewTarget;

/**
 * Created by chhavi on 12/12/15.
 */
public class EmailExplain extends AppCompatActivity {
    ShowcaseView showcaseView;
    TextView body;
    int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_explain);
        final Button startButton = (Button)findViewById(R.id.start_button);
        final TextPaint titlePaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        titlePaint.setColor(this.getResources().getColor(R.color.colorPrimaryDark, getTheme()));
        titlePaint.setTextSize(44.0f);

         showcaseView = new ShowcaseView.Builder(this)
                .withNewStyleShowcase()
                .setTarget(new ViewTarget(R.id.from_text, this))
                .setContentTitle("From Title")
                    .setContentText("This is from where you got the message\n\nआप यहाँ से messaage मिला")//.setContentTitlePaint(titlePaint)
                 .setStyle(R.style.CustomShowcaseTheme2)
                 .build();
          flag = 0;
         body = (TextView)findViewById(R.id.body_text);
       // final ShowcaseView finalShowcaseView = showcaseView;
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == 0) {
                    showcaseView = new ShowcaseView.Builder(EmailExplain.this)
                            .withNewStyleShowcase()
                            .setTarget(new ViewTarget(R.id.to_text, EmailExplain.this)).setContentTitlePaint(titlePaint)
                            .setContentTitle("To Title")
                            .setContentText("This is  where you send the message\n\nयहाँ से संदेश भेजें")
                            .setStyle(R.style.CustomShowcaseTheme2)
                            .build();
                    showcaseView.show();
                   // startButton.setText("Continue");
                  //  showcaseView.show();
                    flag = 1;
                   // showcaseView.setShowcase(new ViewTarget(body),true);
                }else{
                    showcaseView = new ShowcaseView.Builder(EmailExplain.this)
                            .withNewStyleShowcase()
                            .setTarget(new ViewTarget(R.id.body_text, EmailExplain.this))
                            .setContentTitle("Body Title")
                            .setContentText("This is where you type your message\n\nअपना संदेश यहां टंकित करें")
                            .setStyle(R.style.CustomShowcaseTheme2)
                            .build();
                    showcaseView.show();
                }

            }
        });


  /*      ;*/
        //showcaseView.show();

    }
}
