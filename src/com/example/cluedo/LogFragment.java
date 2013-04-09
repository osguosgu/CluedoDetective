package com.example.cluedo;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class LogFragment extends ListFragment{
	
	GameLogic logic;		
	View logView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		super.onCreateView(inflater, container, savedInstanceState);
		System.out.println(getArguments());
		this.logic = (GameLogic) getArguments().getSerializable("GameLogic");
		logView = inflater.inflate(R.layout.tab_3_layout, container, false);
		//system.out.println
		//logic.logAdapter = new ArrayAdapter<String>(logView.getContext(), android.R.layout.simple_list_item_1, logic.names);
	    //setListAdapter(logic.logAdapter);
		return logView;
	
	}
	public void addLogItem(){
			
		System.out.println("-------------------------jes------------------------");
		
	}
}