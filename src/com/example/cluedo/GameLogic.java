
package com.example.cluedo;

import java.util.ArrayList;

import android.widget.ArrayAdapter;

public class GameLogic {

	
	ArrayList<String> names;
	boolean[] active;
	int playerid;
	ArrayAdapter<String> logAdapter;
	ArrayList<Integer> cards;
	
	ArrayList<LogItem> log;
	
	public GameLogic(ArrayList<String> names, boolean[] active, ArrayList<Integer> cards, int playerid){
		this.names = names;
		this.active = active;
		this.cards = cards;
		this.playerid = playerid;
		log = new ArrayList<LogItem>();
	}
	
	public ArrayList<String> getNamesArrayList(){
		return new ArrayList<String>(this.names);
	}
	
	public ArrayList<String> getLogArrayList() {
		ArrayList<String> r = new ArrayList<String>();
		for (int i = 0; i < this.cards.size(); i++ ) {
			r.add(this.cards.get(i).toString());
		}
		return r;
	}

	public void removeFromLog(int index){
		this.log.remove(index);
	}
	
	public void addInput(int asker, int answerer, int room_card, int weapon_card, int character_card) {
		this.log.add(new LogItem(asker, answerer, room_card, weapon_card, character_card));
	}
	
	public void addKnownCard(int id) {
		this.log.add(new LogItem(id));
	}
	
	public class LogItem {
		int type; // What type this log is, 0 for normal thing and 1 for just known card added
		// Things for type 0
		int asker, answerer;
		int room_card, weapon_card, character_card;
		// Things for type 1
		int known_card;
		public LogItem(int card) {
			type = 1;
			this.known_card = card;
		}
		public LogItem(int asker, int answerer, int room_card, int weapon_card, int character_card) {
			this.asker = asker;
			this.answerer = answerer;
			this.room_card = room_card;
			this.character_card = character_card;
			this.weapon_card = weapon_card;
		}
		public String getString() {
			if (type == 0) {
				return "Asked: " + this.room_card + this.weapon_card + this.character_card;
			}
			if (type == 1) {
				return "Known card id was: " + this.known_card;
			}
			return "Error";
		}
	}
}

