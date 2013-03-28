package com.example.cluedo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddCardsActivity extends Activity {
	public final static String EXTRA_NAMES_LIST = "com.example.cluedo.NAMES_LIST";
	public final static String EXTRA_ACTIVE_LIST = "com.example.cluedo.ACTIVE_LIST";
	public final static String EXTRA_MY_CARDS_LIST = "com.example.cluedo.MY_CARDS_LIST";
	public final static String EXTRA_PLAYER_ID = "com.example.cluedo.PLAYER_ID";
	
	ArrayList<String> names;// = new ArrayList<String>();
	boolean[] active;// = new ArrayList<Boolean>();
	int playerid;
	int card_count;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_cards);
		
		// Haetaan intentit
		Intent intent = getIntent();
		active = intent.getBooleanArrayExtra(MainActivity.EXTRA_ACTIVE_LIST);
		names = intent.getStringArrayListExtra(MainActivity.EXTRA_NAMES_LIST);
		
		playerid = intent.getIntExtra(MainActivity.EXTRA_PLAYER_ID, 0);
		// Tähän ehkä pitäs lisätä tarkistuksia yms että intent on oikeanlainen
		// Miten toimii esim noi acitivy lifesyclet näitten kanssa oikein????
		
		
		// Haetaan layoutti ja aletaan lisätä sinne checkboxeja
		LinearLayout layout = (LinearLayout) findViewById(R.id.checkBoxsListView);
		Resources res = getResources();
		int counter = 0;
		TextView text = (TextView) new TextView(this);
		text.setText("Characters");
		layout.addView(text);
		String[] data = res.getStringArray(R.array.character_array);
		for (int i = 0; i < data.length; i++) {
			CheckBox box = (CheckBox) new CheckBox(this);
			box.setText(data[i]);
			box.setId(counter); // Id on kyseisen kortin id
			layout.addView(box);
			counter += 1;
		}
		text = (TextView) new TextView(this);
		text.setText("Weapons");
		layout.addView(text);
		data = res.getStringArray(R.array.weapon_array);
		for (int i = 0; i < data.length; i++) {
			CheckBox box = (CheckBox) new CheckBox(this);
			box.setText(data[i]);
			box.setId(counter); // Id on kyseisen kortin id
			layout.addView(box);
			counter += 1;
		}
		text = (TextView) new TextView(this);
		text.setText("Rooms");
		layout.addView(text);
		data = res.getStringArray(R.array.room_array);
		for (int i = 0; i < data.length; i++) {
			CheckBox box = (CheckBox) new CheckBox(this);
			box.setText(data[i]);
			box.setId(counter); // Id on kyseisen kortin id
			layout.addView(box);
			counter += 1;
		}
		card_count = counter;
		
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_cards, menu);
		return true;
	}
	
	public void continueToGame(View view) {
		
		// Lets get user selected id:s to array
		ArrayList<Integer> selected = new ArrayList<Integer>();
		LinearLayout layout = (LinearLayout) findViewById(R.id.checkBoxsListView);
		for (int i = 0; i < this.card_count; i ++) {
			CheckBox box = (CheckBox) new CheckBox(this);
			box = (CheckBox) layout.findViewById(i);
			if (box.isChecked())
				selected.add(i);
		}

		Intent intent = new Intent(this, GameActivity.class);
		intent.putExtra(EXTRA_NAMES_LIST, names);
		intent.putExtra(EXTRA_ACTIVE_LIST, active);
		intent.putExtra(EXTRA_MY_CARDS_LIST, selected.toArray());
		intent.putExtra(EXTRA_PLAYER_ID, playerid);
		System.out.println("jes0" +names);
		startActivity(intent);
	}
}
