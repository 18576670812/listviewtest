package com.example.listviewtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SimpleAdapterTest2 extends ListActivity implements View.OnClickListener {        
	// private List<String> data = new ArrayList<String>();        
	@Override        
	public void onCreate(Bundle savedInstanceState) {            
		super.onCreate(savedInstanceState);                 
		SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.vlist,                    
				new String[]{"title","info","imgs","imge"},                    
				new int[]{R.id.title,R.id.info,R.id.imgstart,R.id.imgend});            
		setListAdapter(adapter);        
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
	}

	private List<Map<String, Object>> getData() {            
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();                 
		Map<String, Object> map = new HashMap<String, Object>();            
		map.put("title", "G1");            
		map.put("info", "google 1");            
		map.put("imgs", R.drawable.btn_minus_selected);
		map.put("imge", R.drawable.btn_plus_selected);
		list.add(map);
		
		map = new HashMap<String, Object>();            
		map.put("title", "G2");            
		map.put("info", "google 2");            
		map.put("imgs", R.drawable.call_contact);
		map.put("imge", R.drawable.btn_plus_selected);
		list.add(map);                 
		
		map = new HashMap<String, Object>();            
		map.put("title", "G3");            
		map.put("info", "google 3");            
		map.put("imgs", R.drawable.btn_star_big_on);
		map.put("imge", R.drawable.btn_plus_selected);
		list.add(map);                         
		return list;        
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		System.out.println("click view ID = " + arg0.getId());
	}
	
	
}