package com.example.cluedo;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.res.Resources;
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

	View inputView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		System.out.println("Kutsuttiin input fragmentin oncreateviewia");
		
		inputView = inflater.inflate(R.layout.tab_2_layout, container, false);
		

		
		Spinner sp1 = (Spinner) inputView.findViewById(R.id.player_spinner);
		ArrayList<String> names = ((GameActivity)getActivity()).getLogic().getNamesArrayList();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(container.getContext(),
				android.R.layout.simple_spinner_item, names);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp1.setAdapter(adapter);
		
		Spinner sp2 = (Spinner) inputView.findViewById(R.id.player_spinner2);
		ArrayList<String> names2 = new ArrayList<String>(names);
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(container.getContext(),
				android.R.layout.simple_spinner_item, names2);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adapter2.add("No one");
		sp2.setAdapter(adapter2);
		
		
		Button hit = (Button)inputView.findViewById(R.id.submit_suspection_button);
		View.OnClickListener handler = new View.OnClickListener() {
			@Override
			public void onClick(View inputView) {
				System.out.println("Kutsuttu submit suspectionia");
				
				final String[] array = this.getSpinnersData();
				if (array[0].equals(array[4])){
					Toast.makeText(inputView.getContext(),"Asker cannot be the revealer!\nNo Submission done.", Toast.LENGTH_SHORT).show();
					return;
					}
				new AlertDialog.Builder(inputView.getContext())
				.setTitle(array[0].toUpperCase() +" SUSPECTS:")
				.setMessage(array[1] +'\n'+ array[2] +'\n'+ array[3])
				.setIcon(R.drawable.agent)
				.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog, int whichButton) {
				    	//((GameActivity)getActivity()).getLogic().addSubmission("C: "+ array[1] +" W: "+ array[2] +" R: "+ array[3]);
				    	((GameActivity)getActivity()).getLogic().addInput(
				    			((Spinner)getActivity().findViewById(R.id.player_spinner)).getSelectedItemPosition(),
				    			((Spinner)getActivity().findViewById(R.id.player_spinner2)).getSelectedItemPosition(),
				    			((Spinner)getActivity().findViewById(R.id.room_spinner)).getSelectedItemPosition(),
				    			((Spinner)getActivity().findViewById(R.id.weapon_spinner)).getSelectedItemPosition(),
				    			((Spinner)getActivity().findViewById(R.id.suspect_spinner)).getSelectedItemPosition());
				    	
				    	Toast.makeText(InputFragment.this.getActivity().getBaseContext(),"SUBMITTED:"+'\n'+array[1] +'\n'+ array[2] +'\n'+
				    			array[3] + '\n'+'\n'+ array[4].toUpperCase() + " REVEALED CARD!" , Toast.LENGTH_LONG).show();
				    }
				})
				 .setNegativeButton(android.R.string.no, null).show();
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
		};
		hit.setOnClickListener(handler);
		
		//ArrayList<String> all = new ArrayList<String>(Arrays.asList(((GameActivity)getActivity()).getResources().getStringArray(R.array.character_array)));
		//all.addAll(Arrays.asList(((GameActivity)getActivity()).getResources().getStringArray(R.array.weapon_array)));
		//all.addAll(Arrays.asList(((GameActivity)getActivity()).getResources().getStringArray(R.array.room_array)));
		
		Button hit2 = (Button)inputView.findViewById(R.id.add_card_button);
		
		hit2.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View inputView) {
					//System.out.println("Kutsuttu add cardia");
					final ArrayList<Integer> mSelectedItems = new ArrayList<Integer>();  // Where we track the selected items
					//System.out.println("Kutsuttu add cardia1");
					
					Resources res = getResources();
					ArrayList<String> allcards = new ArrayList<String>(Arrays.asList(res.getStringArray(R.array.character_array)));
					allcards.addAll(Arrays.asList(res.getStringArray(R.array.weapon_array)));
					allcards.addAll(Arrays.asList(res.getStringArray(R.array.room_array)));
					
					new AlertDialog.Builder(inputView.getContext())

					.setTitle("Select Known cards")
					.setMultiChoiceItems(allcards.toArray(new CharSequence[allcards.size()]), null,
	                      new DialogInterface.OnMultiChoiceClickListener() {
					               @Override
					               
					               public void onClick(DialogInterface dialog, int which, boolean isChecked) {
					            	   //System.out.println("Kutsuttu add cardia2");
					                   if (isChecked) {
					                       // If the user checked the item, add it to the selected items
					                       mSelectedItems.add(which);
					                   } else if (mSelectedItems.contains(which)) {
					                       // Else, if the item is already in the array, remove it 
					                       mSelectedItems.remove(Integer.valueOf(which));
					                   }
					               }
					           })
					    // Set the action buttons
		           .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
		               @Override
		               public void onClick(DialogInterface dialog, int id) {
		            	   if (mSelectedItems.size() ==0){
		            		   Toast.makeText(InputFragment.this.getActivity().getBaseContext(), "No cards added", Toast.LENGTH_SHORT).show();
		            		   return;
		            	   }
		            	   int count = 0;
		            	   for (int i : mSelectedItems) {
		            		   if(((GameActivity)getActivity()).getLogic().addKnownCard(i)) count += 1;
		            	   }
		            	   if (count == 0) Toast.makeText(InputFragment.this.getActivity().getBaseContext(), "You already know this!", Toast.LENGTH_SHORT).show();
		            	   else if (count == 1) Toast.makeText(InputFragment.this.getActivity().getBaseContext(), count + " card added!", Toast.LENGTH_SHORT).show();
		            	   else Toast.makeText(InputFragment.this.getActivity().getBaseContext(), count +" cards added!", Toast.LENGTH_SHORT).show();
		               }
		               
		           })
		           .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
		               @Override
		               public void onClick(DialogInterface dialog, int id) {
		           }
		        }).show();
			}
		});
		
		return inputView;
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

