
package com.example.cluedo;

import java.io.Serializable;
import java.util.ArrayList;

import android.widget.ListAdapter;

public class GameLogic implements Serializable{

	private static final long serialVersionUID = 1L;
	
	ArrayList<String> names;
	boolean[] active;
	int playerid;
	ArrayList<String> logItems = new ArrayList<String>();
	ListAdapter logAdapter;
	
	public GameLogic(ArrayList<String> names, boolean[] active,
			int playerid){
		this.names = names;
		this.active = active;
		this.playerid = playerid;
	}
	
	public ArrayList<String> getNamesArrayList(){
		return new ArrayList<String>(this.names);
	}
}

