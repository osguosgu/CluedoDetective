package com.example.cluedo;
import android.app.AlertDialog;
import android.app.ListFragment;
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

public class LogFragment extends ListFragment{
	
	GameLogic logic;		
	View logView;
	int currentItem;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		super.onCreateView(inflater, container, savedInstanceState);
		System.out.println(getArguments());
		this.logic = (GameLogic) getArguments().getSerializable("GameLogic");
		
		logView = inflater.inflate(R.layout.tab_3_layout,null);
		
		logic.logAdapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, logic.logItems);
		ListView list = (ListView) logView.findViewById(android.R.id.list);
	    list.setAdapter(logic.logAdapter);
	    list.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View view,
	                int position, long id) {
	        		currentItem = position;
		        	new AlertDialog.Builder(logView.getContext())
		    		.setTitle("Delete log item")
		    		.setMessage("Are you sure?")
		    		.setIcon(R.drawable.agent)
		    		.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
	
		    		    public void onClick(DialogInterface dialog, int whichButton) {
		    		    	logic.removeFromLog(currentItem);
		    		    	logic.updateLog();
		                	Toast.makeText(logView.getContext(), "Log item deleted.", Toast.LENGTH_SHORT).show();	
		    		    }
		    		})
		    		 .setNegativeButton(android.R.string.no, null).show();
	        			
	        }
	    }); 
		//system.out.println
		//String []list = {"jes"};
		//System.out.println(list);
		//logic.logAdapter = new ArrayAdapter<String>(logView.getContext(), android.R.layout.simple_list_item_1, list);
	    //setListAdapter(logic.logAdapter);
		return logView;
	}
}