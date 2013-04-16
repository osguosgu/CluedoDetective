package com.example.cluedo;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;




public class GameActivity extends Activity {

	
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
		this.logic =  new GameLogic(this.names, this.active, this.cards, this.playerid);
		
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
		
		
		
		System.out.println("PEPEPEPEPEPEEP");
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
