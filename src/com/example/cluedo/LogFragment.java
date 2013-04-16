package com.example.cluedo;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LogFragment extends ListFragment{
		
	View logView;
	int currentItem;
	ArrayAdapter<String> logAdapter;
	ListView list;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		//System.out.println(getArguments());
		//this.logic = (GameLogic) getArguments().getSerializable("GameLogic");
		
		logView = inflater.inflate(R.layout.tab_3_layout, null);
		logAdapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, ((GameActivity)getActivity()).getLogic().getLogArrayList());
		
		list = (ListView)logView.findViewById(android.R.id.list);
		
		System.out.println("Tämmönen täälä");
		System.out.println(list.getOnItemClickListener());
		list.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View view, int position,
		            long id) {
		        System.out.println("VITTU KYRPÄ PILLU");
		    	//Toast.makeText(getBaseContext(), "Perse!", Toast.LENGTH_LONG).show();
		    }
		});
		System.out.println("Tämmönen täälä");
		System.out.println(list.getOnItemClickListener());
		
	    list.setAdapter(logAdapter);
		return logView;

		/*
	    list.setOnItemClickListener(new OnItemClickListener() {
	    	@Override
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	        		System.out.println("Kutsuttiin poistoa");
	        		currentItem = position;
		        	new AlertDialog.Builder(logView.getContext())
		    		.setTitle("Delete log item")
		    		.setMessage("Are you sure?")
		    		.setIcon(R.drawable.agent)
		    		.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
		    			@Override
		    		    public void onClick(DialogInterface dialog, int whichButton) {
		    		    	((GameActivity)getActivity()).getLogic().removeFromLog(currentItem);
		    		    	((GameActivity)getActivity()).getLogic().updateLog();
		                	Toast.makeText(logView.getContext(), "Log item deleted.", Toast.LENGTH_SHORT).show();	
		    		    }
		    		})
		    		 .setNegativeButton(android.R.string.no, null).show();
	        			
	        }
	    });
	    */
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
}