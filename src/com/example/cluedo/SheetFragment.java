package com.example.cluedo;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class SheetFragment extends Fragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("Kutsuttiin on create viewiä sheet fragmentista");
		View sheetView = inflater.inflate(R.layout.tab_1_layout, container, false);
		TableLayout table = (TableLayout) sheetView.findViewById(R.id.tableLayout1);
		/*
		Resources res = getResources();
		System.out.println("hahahalololololo");
		String[] data = res.getStringArray(R.array.character_array);
		System.out.println("hahahalololololo");
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
			TableRow row = new TableRow(container.getContext());
			TextView t = new TextView(container.getContext());
			t.setText(data[i]);
			row.addView(t);
			table.addView(row, new TableLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		}*/
		
		return sheetView;
	}
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
	}
}
