
package com.example.cluedo;

import java.io.Serializable;
import java.util.ArrayList;

public class GameLogic implements Serializable{

	private static final long serialVersionUID = 1L;
	
	ArrayList<String> names;
	boolean[] active;
	int playerid;
	
	
	public GameLogic(ArrayList<String> names, boolean[] active,
			int playerid){
		this.names = names;
		this.active = active;
		this.playerid = playerid;
		
		System.out.println("jes2" +names);
	}
	
	public ArrayList<String> getNamesArrayList(){
		return this.names;
	}
}

