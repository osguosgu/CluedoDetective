package com.example.cluedo;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class LogFragment extends Fragment{
		
	int currentItem;
	View logView;
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
		
		System.out.println("Aaa");
		list = (ListView)logView.findViewById(R.id.logListView);
		System.out.println("BBBB");

	    list.setAdapter(logAdapter);
	    
		System.out.println("Tämmönen täälä");
		System.out.println(list.getOnItemClickListener());

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
		    				logAdapter.remove(logAdapter.getItem(currentItem));
		    		    	((GameActivity)getActivity()).getLogic().removeFromLog(currentItem);
		                	Toast.makeText(logView.getContext(), "Log item deleted.", Toast.LENGTH_SHORT).show();	
		    		    }
		    		})
		    		 .setNegativeButton(android.R.string.no, null).show();
	        			
	        }
	    });
		System.out.println("Tämmönen täälä");
		System.out.println(list.getOnItemClickListener());
		System.out.println(getActivity().getCurrentFocus());
		System.out.println(logView.getFocusables(currentItem));
	    
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
}