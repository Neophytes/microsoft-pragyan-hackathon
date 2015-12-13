package com.chhavi.pragyan_hackathon.panchayat;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chhavi.pragyan_hackathon.R;

/**
 * This is demo code to accompany the Mobiletuts+ tutorial:
 * - Android SDK: Create a Hangman Game
 * 
 * Sue Smith - January 2014
 */

public class GameActivity extends Activity {

	//the words
	private String[] words;
	private String[] hints;
	//random for word selection
	private Random rand;
	//store the current word
	private String currWord;
	//the layout holding the answer
	private LinearLayout wordLayout;
	//text views for each letter in the answer
	private TextView[] charViews;
	private TextView hintView;
	//letter button grid
	private GridView letters;
	//letter button adapter
	private LetterAdapter ltrAdapt;
	//body part images
	private ImageView[] bodyParts;
	//total parts
	private int numParts=6;
	//current part
	private int currPart;
	//num chars in word
	private int numChars;
	//num correct so far
	private int numCorr;
	//help
	private AlertDialog helpAlert;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		//read answer words in
		Resources res = getResources();
		words = res.getStringArray(R.array.words);
		hints = res.getStringArray(R.array.hints);

		//initialize random
		rand = new Random();
		//initialize word
		currWord="";

		//get answer area
		wordLayout = (LinearLayout)findViewById(R.id.word);
		hintView = (TextView)findViewById(R.id.hintText);

		//get letter button grid
		letters = (GridView)findViewById(R.id.letters);

		//get body part images
		bodyParts = new ImageView[numParts];
		bodyParts[0] = (ImageView)findViewById(R.id.head);
		bodyParts[1] = (ImageView)findViewById(R.id.body);
		bodyParts[2] = (ImageView)findViewById(R.id.arm1);
		bodyParts[3] = (ImageView)findViewById(R.id.arm2);
		bodyParts[4] = (ImageView)findViewById(R.id.leg1);
		bodyParts[5] = (ImageView)findViewById(R.id.leg2);

		//set home as up
	//	getActionBar().setDisplayHomeAsUpEnabled(true);

		//start gameplay
		playGame();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.action_help:
			showHelp();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	//play a new game
	private void playGame(){

		//choose a word
		String hint;
		int i = rand.nextInt(words.length);
		String newWord = words[i];
		hint = hints[i];
		//make sure not same word as last time
		while(newWord.equals(currWord)) {
			i = rand.nextInt(words.length);
			newWord = words[i];
			hint = hints[i];
		}
		//update current word
		currWord = newWord;
		hintView.setText(hint);

		//create new array for character text views
		charViews = new TextView[currWord.length()];

		//remove any existing letters
		wordLayout.removeAllViews();

		//loop through characters
		for(int c=0; c<currWord.length(); c++){
			charViews[c] = new TextView(this);
			//set the current letter
			charViews[c].setText(""+currWord.charAt(c));
			//set layout
			charViews[c].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, 
					LayoutParams.WRAP_CONTENT));
			charViews[c].setGravity(Gravity.CENTER);
			charViews[c].setTextColor(Color.WHITE);
			charViews[c].setBackgroundResource(R.drawable.letter_bg);
			//add to display
			wordLayout.addView(charViews[c]);
		}

		//reset adapter
		ltrAdapt=new LetterAdapter(this);
		letters.setAdapter(ltrAdapt);

		//start part at zero
		currPart=0;
		//set word length and correct choices
		numChars=currWord.length();
		numCorr=0;

		//hide all parts
		for(int p=0; p<numParts; p++){
			bodyParts[p].setVisibility(View.INVISIBLE);
		}
	}

	//letter pressed method
	public void letterPressed(View view){
		//find out which letter was pressed
		String ltr=((TextView)view).getText().toString();
		char letterChar = ltr.charAt(0);
		//disable view
		view.setEnabled(false);
		view.setBackgroundResource(R.drawable.letter_down);
		//check if correct
		boolean correct=false;
		for(int k=0; k<currWord.length(); k++){
			Log.e("Tag"+letterChar, currWord.charAt(k)+"");
			if(currWord.charAt(k)==letterChar){
				correct=true;
				numCorr++;
				charViews[k].setTextColor(Color.BLACK);
			}
		}
		//check in case won
		if(correct){
			if(numCorr==numChars){
				//disable all buttons
				disableBtns();
				//let user know they have won, ask if they want to play again
				AlertDialog.Builder winBuild = new AlertDialog.Builder(this);
				winBuild.setTitle("YAY");
				winBuild.setMessage("You win!\n\nThe answer was:\n\n"+currWord);
				winBuild.setPositiveButton("Play Again", 
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						GameActivity.this.playGame();
					}});
				winBuild.setNegativeButton("Exit", 
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						GameActivity.this.finish();
					}});
				winBuild.show();
			}
		}
		//check if user still has guesses
		else if(currPart<numParts){
			//show next part
			bodyParts[currPart].setVisibility(View.VISIBLE);
			currPart++;
		}
		else{
			//user has lost
			disableBtns();
			//let the user know they lost, ask if they want to play again
			AlertDialog.Builder loseBuild = new AlertDialog.Builder(this);
			loseBuild.setTitle("OOPS");
			loseBuild.setMessage("You lose!\n\nThe answer was:\n\n"+currWord);
			loseBuild.setPositiveButton("Play Again", 
					new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					GameActivity.this.playGame();
				}});
			loseBuild.setNegativeButton("Exit", 
					new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					GameActivity.this.finish();
				}});
			loseBuild.show();
		}
	}

	//disable letter buttons
	public void disableBtns(){	
		int numLetters = letters.getChildCount();
		for(int l=0; l<numLetters; l++){
			letters.getChildAt(l).setEnabled(false);
		}
	}

	//show help information
	public void showHelp(){
		AlertDialog.Builder helpBuild = new AlertDialog.Builder(this);
		helpBuild.setTitle("Help");
		helpBuild.setMessage("Guess the word by selecting the letters.\n\n"
				+ "You only have 6 wrong selections then it's game over!");
		helpBuild.setPositiveButton("OK", 
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				helpAlert.dismiss();
			}});
		helpAlert = helpBuild.create();
		helpBuild.show();
	}

}
