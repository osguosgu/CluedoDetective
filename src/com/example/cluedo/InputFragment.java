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
		
		Spinner sp1 = (Spinner) inputView.findViewById(R.id.player_spinner);
		ArrayList<String> names = logic.getNamesArrayList();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(container.getContext(),
				android.R.layout.simple_spinner_dropdown_item, names);
		sp1.setAdapter(adapter);
		
		Spinner sp2 = (Spinner) inputView.findViewById(R.id.player_spinner2);
		ArrayList<String> names2 = names;
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(container.getContext(),
				android.R.layout.simple_spinner_dropdown_item, names2);
		adapter2.add("No one");
		sp2.setAdapter(adapter2);
		if (adapter.equals(adapter2)) System.out.println("paskaaaaa");
		//System.out.println(names.get(0));
		/*
		ArrayAdapter<CharSequence> adapter;
		System.out.println(R.array.character_array);
		System.out.println(container.getContext());
		adapter = ArrayAdapter.createFromResource(container.getContext() , R.array.character_array, android.R.layout.simple_spinner_dropdown_item);
		System.out.println(R.array.character_array);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(adapter);
		*/
		return inputView;
	}
	
}