package com.example.cluedo;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class InputFragment extends Fragment {
	
	GameLogic logic;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		System.out.println(getArguments());
		this.logic = (GameLogic) getArguments().getSerializable("GameLogic");
		View inputView = inflater.inflate(R.layout.tab_2_layout, container, false);
		Spinner sp = (Spinner) inputView.findViewById(R.id.suspect_spinner);
		ArrayList<String> names = logic.getNamesArrayList();
		System.out.println(names.get(0));
		/*
		ArrayAdapter<CharSequence> adapter;
		System.out.println(R.array.character_array);
		System.out.println(container.getContext());
		adapter = ArrayAdapter.createFromResource(container.getContext() , R.array.character_array, android.R.layout.simple_spinner_dropdown_item);
		System.out.println(R.array.character_array);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(adapter);
		*/
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(container.getContext(),
				android.R.layout.simple_spinner_dropdown_item, names);
		sp.setAdapter(adapter);
		
		return inputView;
	}
	
}