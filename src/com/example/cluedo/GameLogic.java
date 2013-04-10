
package com.example.cluedo;

import java.io.Serializable;
import java.util.ArrayList;

import android.widget.ArrayAdapter;

public class GameLogic implements Serializable{

	private static final long serialVersionUID = 1L;
	
	ArrayList<String> names;
	boolean[] active;
	int playerid;
	ArrayList<String> logItems = new ArrayList<String>();
	ArrayAdapter<String> logAdapter;
	
	public GameLogic(ArrayList<String> names, boolean[] active,
			int playerid){
		this.names = names;
		this.active = active;
		this.playerid = playerid;
	}
	
	public ArrayList<String> getNamesArrayList(){
		return new ArrayList<String>(this.names);
	}
	
	public void addSubmission(String jes){
		this.logItems.add(jes);
		this.logAdapter.notifyDataSetChanged();
	}
}

