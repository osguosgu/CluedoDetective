package com.example.cluedo;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class InputFragment extends Fragment {
	
	GameLogic logic;
	Button hit;
	View inputView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		System.out.println(getArguments());
		this.logic = (GameLogic) getArguments().getSerializable("GameLogic");
		inputView = inflater.inflate(R.layout.tab_2_layout, container, false);
		
		Spinner sp1 = (Spinner) inputView.findViewById(R.id.player_spinner);
		ArrayList<String> names = logic.getNamesArrayList();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(container.getContext(),
				android.R.layout.simple_spinner_dropdown_item, names);
		sp1.setAdapter(adapter);
		
		Spinner sp2 = (Spinner) inputView.findViewById(R.id.player_spinner2);
		ArrayList<String> names2 = new ArrayList<String>(names);
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(container.getContext(),
				android.R.layout.simple_spinner_dropdown_item, names2);
		adapter2.add("No one");
		sp2.setAdapter(adapter2);
		
		hit = (Button)inputView.findViewById(R.id.print_button);
		View.OnClickListener handler = new View.OnClickListener() {
			
			@Override
			public void onClick(View inputView) {
				InputFragment.this.submitSuspection(inputView);
				
			}
		};
		
		hit.setOnClickListener(handler);
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

	public void submitSuspection(View v){
		String[] array = this.getSpinnersData();
    	Toast.makeText(inputView.getContext(),"Someone has to suspect!?", Toast.LENGTH_SHORT).show();
    		
		new AlertDialog.Builder(inputView.getContext())
		.setTitle(array[0].toUpperCase() +" SUSPECTS:")
		.setMessage(array[1] +'\n'+ array[2] +'\n'+ array[3])
		.setIcon(R.drawable.agent)
		.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

		    public void onClick(DialogInterface dialog, int whichButton) {
		    	String[] array = InputFragment.this.getSpinnersData();
		    	
		    	Toast.makeText(inputView.getContext(),"SUBMITTED:"+'\n'+array[1] +'\n'+ array[2] +'\n'+ array[3] + '\n'+'\n'+ array[4].toUpperCase() + " REVEALED CARD!" , Toast.LENGTH_LONG).show();
		    }
		})
		 .setNegativeButton(android.R.string.no, null).show();
		
		//Toast.makeText(this, player.toUpperCase() +" SUSPECTS:"+'\n'+'\n'+suspect +'\n'+ weapon +'\n'+ room  , Toast.LENGTH_SHORT).show();
    	
    }
	public String [] getSpinnersData(){
		Spinner p = (Spinner)inputView.findViewById(R.id.player_spinner);
		Spinner p2 = (Spinner)inputView.findViewById(R.id.player_spinner2);
		Spinner s = (Spinner)inputView.findViewById(R.id.suspect_spinner);
		Spinner ss = (Spinner)inputView.findViewById(R.id.weapon_spinner);
		Spinner sss = (Spinner)inputView.findViewById(R.id.room_spinner);
		String player = (String)p.getSelectedItem();
		String player2 = (String)p2.getSelectedItem();
		String suspect = (String)s.getSelectedItem();
		String weapon = (String)ss.getSelectedItem();
		String room = (String)sss.getSelectedItem();
		String [] array = {player,suspect,weapon,room,player2};
		return array;
	}
	
}