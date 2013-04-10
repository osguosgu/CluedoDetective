package com.example.cluedo;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LogFragment extends Fragment{
	
	GameLogic logic;		
	View logView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		super.onCreateView(inflater, container, savedInstanceState);
		System.out.println("Kutsuttiin on create viewiä log fragmentissa");
		this.logic = (GameLogic) getArguments().getSerializable("GameLogic");
		
		logView = inflater.inflate(R.layout.tab_3_layout,null);
		ListView list = (ListView) logView.findViewById(android.R.id.list);
		logic.logAdapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, logic.logItems);
        list.setAdapter(logic.logAdapter);
		//system.out.println
		//String []list = {"jes"};
		//System.out.println(list);
		//logic.logAdapter = new ArrayAdapter<String>(logView.getContext(), android.R.layout.simple_list_item_1, list);
	    //setListAdapter(logic.logAdapter);
		return logView;
	}
}