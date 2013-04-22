package com.example.cluedo;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class SheetFragment extends Fragment{ /*
	GameLogic logic;
	String[] characters, weapons, rooms;
	View inputView;
	TableLayout thistable;
	Boolean hidden;*/
	View inputView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("Kutsuttiin on create viewiä sheet fragmentista");
		
		inputView = inflater.inflate(R.layout.tab_1_layout, container, false);

		inputView.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Activity activity = getActivity();
				if (activity == null) return;
				//if (this.hidden) {
				//	for (int i = 0; i < 10; i++) {
				//		TableRow row = (TableRow) inputView.findViewById(R.id.tableLayout1).findViewById(i);
				//		row.startAnimation(AnimationUtils.loadAnimation(activity, android.R.anim.fade_out));
				//		row.setVisibility(View.INVISIBLE);
				//	}
				/*	this.hidden = false;
				}
				else {
					for (int i = 0; i < 10; i++) {
						TableRow row = (TableRow) thistable.findViewById(i);
						row.startAnimation(AnimationUtils.loadAnimation(activity, android.R.anim.fade_out));
						row.setVisibility(View.INVISIBLE);
					}
					this.hidden = true;
				}*/

						
			}
		});
		
		updateTable();
		
		return inputView;
	}

	@Override
	public void onAttach(Activity activity) {
		System.out.println("HeiheiheiJASDFFJDSDFJKLJLADFKSDJKLÖJKLÖSDJLÖSDJKLÖSDFJÖ");
		super.onAttach(activity);
	}
	
	public void updateTable() {
		// Lets update gamelogic
		GameLogic logic = ((GameActivity) getActivity()).getLogic();
		logic.updateSheetData();
		
		// Get and clear the table
		TableLayout table = (TableLayout) inputView.findViewById(R.id.tableLayout1);
		table.removeAllViews();
		
		ArrayList<String> players = ((GameActivity) getActivity()).getLogic().getNamesArrayList();
		TableRow row;
		row = new TableRow(inputView.getContext());
		TextView t = new TextView(inputView.getContext());
		row.addView(t);
		
		
		for (int i = 0; i < players.size(); i++) {
			t = new TextView(inputView.getContext());
			t.setPadding(8,8,8,8);
			t.setText(players.get(i));
			row.addView(t);
		}
		table.addView(row, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			
		Resources res = getActivity().getResources();
		String[] string;
		string = res.getStringArray(R.array.character_array);

		int counter = 0;
		for (int i = 0; i < string.length; i++) {
			row = new TableRow(inputView.getContext());
			t = new TextView(inputView.getContext());
			t.setText(string[i]);
			t.setPadding(8,8,8,8);
			row.addView(t);
			for (int j = 0; j < players.size(); j++) {
				t = new TextView(inputView.getContext());
				t.setText(logic.getDataAt(counter, j));
				t.setPadding(8,8,8,8);
				row.addView(t);
			}
			row.setId(counter);
			counter += 1;
			table.addView(row, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		}
		string = res.getStringArray(R.array.weapon_array);
		for (int i = 0; i < string.length; i++) {
			row = new TableRow(inputView.getContext());
			t = new TextView(inputView.getContext());
			t.setText(string[i]);
			t.setPadding(8,8,8,8);
			row.addView(t);
			for (int j = 0; j < players.size(); j++) {
				t = new TextView(inputView.getContext());
				t.setText(logic.getDataAt(counter, j));
				t.setPadding(8,8,8,8);
				row.addView(t);
			}
			row.setId(counter);
			counter += 1;
			table.addView(row, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		}
		string = res.getStringArray(R.array.room_array);
		for (int i = 0; i < string.length; i++) {
			row = new TableRow(inputView.getContext());
			t = new TextView(inputView.getContext());
			t.setText(string[i]);
			t.setPadding(8,8,8,8);
			row.addView(t);
			for (int j = 0; j < players.size(); j++) {
				t = new TextView(inputView.getContext());
				t.setText(logic.getDataAt(counter, j));
				t.setPadding(8,8,8,8);
				row.addView(t);
			}
			row.setId(counter);
			counter += 1;
			table.addView(row, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		}
	}

}
