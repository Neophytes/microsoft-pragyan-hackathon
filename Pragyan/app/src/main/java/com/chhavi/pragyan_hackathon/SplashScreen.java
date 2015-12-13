package com.chhavi.pragyan_hackathon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

/**
 * Created by jigyasa on 13/12/15.
 */
public class SplashScreen extends Activity {

    private Thread mSplashThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        ScaleAnimation scaleAnim1 = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f);
        scaleAnim1.setDuration(500);
        scaleAnim1.setStartOffset(400);
        scaleAnim1.setFillAfter(true);

        ScaleAnimation scaleAnim12 = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f);
        scaleAnim12.setDuration(800);
        scaleAnim12.setStartOffset(900);

        //TextView justSpecial = (TextView)findViewById(R.id.just_special_text);
        //justSpecial.setVisibility(View.VISIBLE);
        //justSpecial.setAnimation(scaleAnim1);

        ImageView owl = (ImageView)findViewById(R.id.imageView1);
        owl.setVisibility(View.VISIBLE);
        owl.setAnimation(scaleAnim1);

        ImageView sah = (ImageView)findViewById(R.id.imageView2);
        sah.setVisibility(View.VISIBLE);
        sah.setAnimation(scaleAnim1);

        TextView hindiWords1 = (TextView)findViewById(R.id.textView25);
        hindiWords1.setVisibility(View.VISIBLE);
        hindiWords1.setAnimation(scaleAnim12);

        TextView hindiWords2 = (TextView)findViewById(R.id.textView26);
        hindiWords2.setVisibility(View.VISIBLE);
        hindiWords2.setAnimation(scaleAnim12);

        TextView engcaption = (TextView)findViewById(R.id.textView27);
        engcaption.setVisibility(View.VISIBLE);
        engcaption.setAnimation(scaleAnim12);

        //Party.main();

        final SplashScreen sPlashScreen = this;

        // The thread to wait for splash screen events
        mSplashThread =  new Thread(){
            @SuppressWarnings("deprecation")
            @Override
            public void run(){
                try {
                    synchronized(this){
                        // Wait given period of time or exit on touch
                        wait(2500);
                    }
                }
                catch(InterruptedException ex){
                }

                finish();

                // Run next activity
                Intent intent = new Intent();
                intent.setClass(sPlashScreen, ChooseAvatar.class);
                startActivity(intent);
                //    stop();
            }
        };

        mSplashThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent evt)
    {
        if(evt.getAction() == MotionEvent.ACTION_DOWN)
        {
            synchronized(mSplashThread){
                mSplashThread.notifyAll();
            }
        }
        return true;
    }

    @Override
    public void onNewIntent(Intent intent) {
        this.setIntent(intent);
    }
}
