package com.example.listviewtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class SimpleAdapterTest1 extends ListActivity {     
	@Override     
	public void onCreate(Bundle savedInstanceState) {         
		super.onCreate(savedInstanceState);                  
		SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.simple, 
				new String[] { "title",  "img" }, new int[] { R.id.title, R.id.img });         
		setListAdapter(adapter);     
	}      
	
	private List<Map<String, Object>> getData() {         
		//map.put(参数名字,参数值)         
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();         
		Map<String, Object> map = new HashMap<String, Object>();         
		map.put("title", "摩托罗拉");         
		map.put("img", R.drawable.btn_close_pressed);         
		list.add(map);                  
		map = new HashMap<String, Object>();         
		map.put("title", "诺基亚");         
		map.put("img", R.drawable.btn_code_lock_touched);         
		list.add(map);                  
		map = new HashMap<String, Object>();         
		map.put("title", "三星");         
		map.put("img", R.drawable.btn_dialog_pressed);         
		list.add(map);         
		return list;         
	}
}