package com.example.cluedo;

import java.util.ArrayList;
import java.util.Locale;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class GameActivity extends FragmentActivity implements
		ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	
	ArrayList<String> names;// = new ArrayList<String>();
	boolean[] active;// = new ArrayList<Boolean>();
	int playerid;
	int card_count;
	GameLogic logic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		// Haetaan intentit
		Intent intent = getIntent();
		this.active = intent.getBooleanArrayExtra(MainActivity.EXTRA_ACTIVE_LIST);
		this.names = intent.getStringArrayListExtra(MainActivity.EXTRA_NAMES_LIST);
		this.playerid = intent.getIntExtra(MainActivity.EXTRA_PLAYER_ID, 0);
		System.out.println("jes1" + this.names);
		this.logic =  new GameLogic(this.names, this.active, this.playerid);
		
		
		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
		mViewPager.setCurrentItem(1);
		
	}
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;	
		
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
		new AlertDialog.Builder(this)
		.setTitle(R.string.help)
		.setMessage("\nEn jaksa kirjottaa...\n")
		.setIcon(R.drawable.agent)
		.setPositiveButton(android.R.string.yes, null).show();
		return true;
		
	}
	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			Fragment fragment = new SectionFragment();
			Bundle args = new Bundle();
			args.putInt(SectionFragment.ARG_SECTION_NUMBER, position + 1);
			args.putSerializable("GameLogic", GameActivity.this.logic);
			//args.putSerializable("jes", GameActivity.this.logic);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class SectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public SectionFragment() {
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View sheetView = inflater.inflate(R.layout.tab_1_layout,container, false);
			
			switch(getArguments().getInt(ARG_SECTION_NUMBER)){
			
			case 1:
				return sheetView;
			case 2:
				InputFragment input_fragment = new InputFragment();
				Bundle b = new Bundle();
				b.putSerializable("GameLogic", getArguments().getSerializable("GameLogic"));
				input_fragment.setArguments(b);
				return input_fragment.onCreateView(inflater, container, savedInstanceState);
			case 3:
				//LogFragment log_fragment = new LogFragment();
				//Bundle bb = new Bundle();
				//bb.putSerializable("GameLogic", getArguments().getSerializable("GameLogic"));
				//log_fragment.setArguments(bb);
				//return log_fragment.onCreateView(inflater, container, savedInstanceState);
				return sheetView;
			}
		return sheetView;
		}
	}
	
}

	
