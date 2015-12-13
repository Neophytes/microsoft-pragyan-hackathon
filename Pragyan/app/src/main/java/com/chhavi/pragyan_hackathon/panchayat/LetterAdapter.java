package com.chhavi.pragyan_hackathon.panchayat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.chhavi.pragyan_hackathon.R;

/**
 * This is demo code to accompany the Mobiletuts+ tutorial:
 * - Android SDK: Create a Hangman Game
 * 
 * Sue Smith - January 2014
 */

public class LetterAdapter extends BaseAdapter {

	//store letters
	private String[] letters;
	//inflater for button layout
	private LayoutInflater letterInf;

	public LetterAdapter(Context c) {
		//instantiate alphabet array
		letters=new String[26];
		for(int a=0; a<letters.length; a++){
			letters[a]=""+(char)(a+'A');
		}
		//specify layout to inflate
		letterInf = LayoutInflater.from(c);
	}

	@Override
	public int getCount() {
		return letters.length;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//create a button for the letter at this position in the alphabet
		Button letterBtn;
		if (convertView == null) {  
			//inflate the button layout
			letterBtn = (Button)letterInf.inflate(R.layout.letter, parent, false);
		} else {
			letterBtn = (Button) convertView;
		}
		//set the text to this letter
		letterBtn.setText(letters[position]);
		return letterBtn;
	}

}
