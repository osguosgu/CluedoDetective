package com.example.cluedo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.res.Resources;
import android.widget.ArrayAdapter;

public class GameLogic extends Activity{


	ArrayList<String> names;
	boolean[] active;
	int playerid, card_amount;
	ArrayAdapter<String> logAdapter;
	ArrayList<Integer> cards;

	ArrayList<LogItem> log;
	Resources res;
	GridStatus[][] grid;

	public GameLogic(ArrayList<String> names, boolean[] active, ArrayList<Integer> cards, int playerid, int card_amount, Resources resource){
		System.out.println(names);
		System.out.println(active);
		System.out.println(cards);
		System.out.println(playerid);
		this.names = names;
		this.active = active;
		this.cards = cards;
		this.playerid = playerid;
		this.card_amount = card_amount;
		log = new ArrayList<LogItem>();
		this.updateSheetData();
		res = resource;

	}

	public ArrayList<String> getNamesArrayList(){
		return new ArrayList<String>(this.names);
	}

	public ArrayList<String> getLogArrayList() {
		ArrayList<String> r = new ArrayList<String>();
		for (int i = 0; i < this.log.size(); i++ ) {
			r.add(this.log.get(i).getString());
		}
		return r;
	}

	public void removeFromLog(int index){
		this.log.remove(index);
	}

	public void addInput(int asker, int answerer, int room_card, int weapon_card, int character_card) {

		this.log.add(new LogItem(asker, answerer, room_card, weapon_card, character_card));
	}

	public boolean addKnownCard(int id) {
		LogItem logItem = new LogItem(id);
		for (LogItem l : log){
			if (l.known_card == id) return false;
		} 
		this.log.add(logItem);
		return true;

	}

	public void updateSheetData() {
		// This needs to be called so that getDataAt returns things that are up to date
		grid = new GridStatus[this.card_amount][this.names.size()];
		int counter = 1;
		for (int i = 0; i < this.card_amount; i++){
			for (int j = 0; j < this.names.size(); j++) {
				grid[i][j] = new GridStatus();
			}
		}
		//int guess_id = 0;
		for (LogItem i : log) {

			if (i.type == 1) {
				for (int j = 0; j < this.names.size(); j++) {
					grid[i.known_card][this.playerid].is_known = true;
				}
				for (int j = this.playerid + 1 ; j <= this.names.size() ; j++){
					grid[i.known_card][j % this.names.size()].can_have = false;
					grid[i.known_card][j % this.names.size()].guess.clear();
				}
			}
			if (i.type == 0) {
				int cards = 6; //how many character and weapon cards there is
				for (int j = i.asker + 1 ; j < this.names.size(); j++){

					if (j % this.names.size() != i.answerer){
						if (i.answerer == this.playerid) continue;

						grid[i.character_card][j].can_have = false;
						grid[i.character_card][j].guess.clear();

						grid[i.weapon_card + cards][j].can_have = false;
						grid[i.weapon_card + cards][j].guess.clear();

						grid[i.room_card + cards*2][j].can_have = false;
						grid[i.room_card + cards*2][j].guess.clear();
					}
					else if(j == i.answerer){
						if (!grid[i.character_card][this.playerid].is_known & grid[i.character_card][this.playerid].can_have)
							grid[i.character_card][j].guess.add(counter);

						if (!grid[i.weapon_card + cards][this.playerid].is_known & grid[i.weapon_card][this.playerid].can_have)
							grid[i.weapon_card + cards][j].guess.add(counter);

						if (!grid[i.room_card + cards*2][this.playerid].is_known & grid[i.room_card][this.playerid].can_have)
							grid[i.room_card + cards*2][j].guess.add(counter);
						counter += 1;
						break;
					}
				}
			}
		}
		this.conclusion();
	}	
	public void conclusion(){
		for (int i = 0; i < this.card_amount ; i++){
			for (int j = 0; j < this.names.size() ; j++){
				if (grid[i][j].is_known) break;
				else if (grid[i][j].guess.size() > 0){
					for (int y : grid[i][j].guess){
						boolean only_one = true;
						for (int x = i + 1; x < i + this.card_amount ; x++){
							if (grid[x % this.card_amount][j].guess.contains(y)){
								only_one = false;
								break;
							}
						}

						if (only_one){
							grid[i][this.playerid].is_known = true;
							grid[i][j].guess.clear();
							for (int k = this.playerid + 1 ; k <= this.names.size() ; k++){
								grid[i][k % this.names.size()].can_have = false;
								grid[i][k % this.names.size()].guess.clear();
							}
							i = 0;
							j = 0;
						}
					}
				}
			}
		}

	}
	public int getDataAt(int card_id, int player_id) {
		//System.out.println(this.grid);
		if (this.grid[card_id][player_id].is_known)
			return 0;
		else if (!this.grid[card_id][player_id].can_have)
			return 1;//"X";
		return 2;
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
			type = 0;
			this.asker = asker;
			this.answerer = answerer;
			this.room_card = room_card;
			this.character_card = character_card;
			this.weapon_card = weapon_card;
		}
		public String getString() {
			String [] characters = res.getStringArray(R.array.character_array);
			String [] weapons = res.getStringArray(R.array.weapon_array);
			String [] rooms = res.getStringArray(R.array.room_array);
			if (type == 0) {
				//System.out.println("been there!!!!!");
				return characters[this.character_card] +", "+ weapons[this.weapon_card] +", "+ rooms[this.room_card];
			}
			if (type == 1) {
				//System.out.println(this.known_card);
				if (this.known_card < 6) return "Added known card: " + characters[this.known_card];
				else if (this.known_card < 12 ) return "Added known card: " + weapons[this.known_card - 6];
				else return "Added known card: " + rooms[this.known_card - 12];	
			}
			return "Error";
		}
	}

	public class GridStatus {

		Boolean is_known;
		Boolean can_have;
		ArrayList<Integer> guess;
		public GridStatus() {
			guess = new ArrayList<Integer>();
			is_known = false;
			can_have = true;
		}
	}
}
