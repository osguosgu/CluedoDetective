package com.example.cluedo;

import java.util.ArrayList;

import android.app.Application;

public class MyApp extends Application {
	ArrayList<String> player_names;
	ArrayList<Boolean> player_active;
	int my_number;
	
	public void setEverything(ArrayList<String> _names, ArrayList<Boolean> _active, int _number){
		player_active = _active;
		player_names = _names;
		my_number = _number;
	}
	public String getName(int i) {
		return player_names.get(i);
	}
	public Boolean getActive(int i) {
		return player_active.get(i);
	}
	public int getPlayerIndex() {
		return my_number;
	}
}
