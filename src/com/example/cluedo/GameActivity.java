package com.example.cluedo;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;




public class GameActivity extends FragmentActivity {

	
	ArrayList<String> names;// = new ArrayList<String>();
	boolean[] active;// = new ArrayList<Boolean>();
	int playerid;
	int card_count;
	ArrayList<Integer> cards; 
	GameLogic logic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Getting info from previois activity
		Intent intent = getIntent();
		this.active = intent.getBooleanArrayExtra(MainActivity.EXTRA_ACTIVE_LIST);
		this.names = intent.getStringArrayListExtra(MainActivity.EXTRA_NAMES_LIST);
		this.cards = intent.getIntegerArrayListExtra(AddCardsActivity.EXTRA_MY_CARDS_LIST);
		this.playerid = intent.getIntExtra(MainActivity.EXTRA_PLAYER_ID, 0);
		
		card_count = getResources().getStringArray(R.array.character_array).length;
		card_count += getResources().getStringArray(R.array.room_array).length;
		card_count += getResources().getStringArray(R.array.weapon_array).length;
		
		this.logic =  new GameLogic(this.names, this.active, this.cards, this.playerid, this.card_count);
		
		for (Integer i : cards)
			this.logic.addKnownCard(i);
		
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		Tab tab = actionBar.newTab();
		tab.setText("sheet");
		TabListener<SheetFragment> t1 = new TabListener<SheetFragment>(this, "sheet", SheetFragment.class);
		tab.setTabListener(t1);
		actionBar.addTab(tab);
		
		tab = actionBar.newTab();
		tab.setText("input");
		TabListener<InputFragment> t2 = new TabListener<InputFragment>(this, "input", InputFragment.class);
		tab.setTabListener(t2);
		actionBar.addTab(tab);
		
		tab = actionBar.newTab();
		tab.setText("log");
		TabListener<LogFragment> t3 = new TabListener<LogFragment>(this, "log", LogFragment.class);
		tab.setTabListener(t3);
		actionBar.addTab(tab);
		
	}
	
	public boolean viewInfo(MenuItem menu){
		new AlertDialog.Builder(this)
		.setTitle("Cluedo Detective")
		.setMessage("by: Rihis&Osku_")
		.setIcon(R.drawable.agent)
		.setPositiveButton(android.R.string.yes, null).show();
		return true;
	}

	public boolean viewHelp(MenuItem menu){
		ActionBar actionBar = getActionBar();
		switch (actionBar.getSelectedTab().getPosition()) {
		case 0:
			new AlertDialog.Builder(this)
			.setTitle(R.string.help)
			.setMessage("\nEkan tabin ohje\n")
			.setIcon(R.drawable.agent)
			.setPositiveButton(android.R.string.yes, null).show();
			break;
		case 1:
			new AlertDialog.Builder(this)
			.setTitle(R.string.help)
			.setMessage("\nJoujou lisätään tapahtumia\n")
			.setIcon(R.drawable.agent)
			.setPositiveButton(android.R.string.yes, null).show();
			break;
		case 2:
			new AlertDialog.Builder(this)
			.setTitle(R.string.help)
			.setMessage("\nHehhee haluutkos poistaa virheitä\n")
			.setIcon(R.drawable.agent)
			.setPositiveButton(android.R.string.yes, null).show();
			break;
		}
		return true;

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;	

	}

	
	private class TabListener<T extends Fragment> implements ActionBar.TabListener {
		private Fragment mFragment;
		private final Activity mActivity;
		private final String mTag;
		private final Class<T> mClass;
		
		public TabListener(Activity activity, String tag, Class<T> clz) {
			mActivity = activity;
			mTag = tag;
			mClass = clz;
		}
		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			if (mFragment == null) {
				mFragment = Fragment.instantiate(mActivity, mClass.getName());
				ft.add(android.R.id.content, mFragment, mTag);
			}
			else {
				ft.attach(mFragment);
			}
		}
		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			if (mFragment != null) {
				ft.detach(mFragment);
			}
		}
		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			
		}
	}
	
	public GameLogic getLogic() {
		return logic;
	}
}
