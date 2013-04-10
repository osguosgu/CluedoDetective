package com.example.cluedo;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class SheetFragment extends Fragment{
	GameLogic logic;
	String[] characters, weapons, rooms;
	View inputView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("Kutsuttiin on create viewiä sheet fragmentista");
		this.inputView = inflater.inflate(R.layout.tab_1_layout, container, false);
		
		this.logic = (GameLogic) getArguments().getSerializable("GameLogic");
		this.characters = (String[]) getArguments().getSerializable("characters");
		this.weapons = (String[]) getArguments().getSerializable("weapons");
		this.rooms = (String[]) getArguments().getSerializable("rooms");
		
		this.updateTable();
		
		return inputView;
	}
	public void updateTable() {
		System.out.println("UPDATE TABLE KUTSUTTU SHEET FRAGMENTISSA!!!!!!!");
		
		TableLayout table = (TableLayout) inputView.findViewById(R.id.tableLayout1);
		
		table.removeAllViews();
		
		ArrayList<String> players = logic.getNamesArrayList();
		TableRow row;
		row = new TableRow(inputView.getContext());
		TextView t;
		for (int i = 0; i < players.size(); i++) {
			t = new TextView(inputView.getContext());
			t.setText(players.get(i));
			//RotateAnimation rotate = (RotateAnimation) AnimationUtils.loadAnimation(inputView.getContext(), R.animator.anim);
			//t.setAnimation(rotate);
			row.addView(t);
		}
		table.addView(row, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			
		for (int i = 0; i < characters.length; i++) {
			System.out.println(characters[i]);
			row = new TableRow(inputView.getContext());
			t = new TextView(inputView.getContext());
			t.setText(characters[i]);
			row.addView(t);
			for (int j = 0; j < players.size(); j++) {
				t = new TextView(inputView.getContext());
				t.setText("0");
				row.addView(t);
			}
			table.addView(row, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		}
		for (int i = 0; i < weapons.length; i++) {
			System.out.println(weapons[i]);
			row = new TableRow(inputView.getContext());
			t = new TextView(inputView.getContext());
			t.setText(weapons[i]);
			row.addView(t);
			for (int j = 0; j < players.size(); j++) {
				t = new TextView(inputView.getContext());
				t.setText("0");
				row.addView(t);
			}
			table.addView(row, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		}
		for (int i = 0; i < rooms.length; i++) {
			System.out.println(rooms[i]);
			row = new TableRow(inputView.getContext());
			t = new TextView(inputView.getContext());
			t.setText(rooms[i]);
			row.addView(t);
			for (int j = 0; j < players.size(); j++) {
				t = new TextView(inputView.getContext());
				t.setText("0");
				row.addView(t);
			}
			table.addView(row, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		}
	}
}
