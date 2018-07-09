package com.example.listviewtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ExtendBaseAdapter extends ListActivity {
	private List<Map<String, Object>> mData;
	@Override        
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mData = getData();
		MyAdapter adapter = new MyAdapter(this);
		setListAdapter(adapter);
	}
	
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "G1");
		map.put("info", "google 1");
		map.put("img", R.drawable.btn_close_pressed);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("title", "G2");
		map.put("info", "google 2");
		map.put("img", R.drawable.btn_close_pressed);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("title", "G3");
		map.put("info", "google 3");
		map.put("img", R.drawable.btn_close_pressed);
		list.add(map);
		return list;
	}
	
	// ListView 中某项被选中后的逻辑
	@Override       
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Log.v("ExtendBaseAdapter-click", (String)mData.get(position).get("title"));
	}
	
	/**
	 * * listview中点击按键弹出对话框
	 */
	public void showInfo(ViewHolder holder){
		new AlertDialog.Builder(this)
			.setTitle("" + holder.title.getText())
			.setMessage("" + holder.info.getText())
			.setPositiveButton("确定", new DialogInterface.OnClickListener() {
				@Override                
				public void onClick(DialogInterface dialog, int which) {
					
				}
			})
			.show();
		}
	
	public final class ViewHolder{
		public ImageView img;
		public TextView title;
		public TextView info;
		public Button viewBtn;
	}
	
	public class MyAdapter extends BaseAdapter{
		private LayoutInflater mInflater;
		public MyAdapter(Context context){
			this.mInflater = LayoutInflater.from(context);
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mData.size();
		}
		
		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				holder=new ViewHolder();
				convertView = mInflater.inflate(R.layout.vlist2, null);
				holder.img = (ImageView)convertView.findViewById(R.id.img);
				holder.title = (TextView)convertView.findViewById(R.id.title);
				holder.info = (TextView)convertView.findViewById(R.id.info);
				holder.viewBtn = (Button)convertView.findViewById(R.id.view_btn);
				System.out.println("R.id.view_btn = " + Integer.toHexString(R.id.view_btn));
				holder.viewBtn.setId(R.id.view_btn+position);
				System.out.println("R.id.view_btn + position(" + position + ")=" 
									+ Integer.toHexString(R.id.view_btn+position));
				convertView.setTag(holder);
			}else {
				holder = (ViewHolder)convertView.getTag();
			}
			
			holder.img.setBackgroundResource((Integer)mData.get(position).get("img"));
			holder.title.setText((String)mData.get(position).get("title"));
			holder.info.setText((String)mData.get(position).get("info"));
			holder.viewBtn.setTag(holder);
			holder.viewBtn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					showInfo((ViewHolder)(v.getTag()));
				}
			});
			
			return convertView;
		}
	}
}

/**
 * 下面将对上述代码，做详细的解释，listView在开始绘制的时候，系统首先调用getCount（）函数，
 * 根据他的返回值得到listView的长度（这也是为什么在开始的第一张图特别的标出列表长度），
 * 然后根据这个长度，调用getView（）逐一绘制每一行。如果你的getCount（）返回值是0的话，
 * 列表将不显示同样return 1，就只显示一行。系统显示列表时，首先实例化一个适配器（这里将实例化自定义的适配器）。
 * 当手动完成适配时，必须手动映射数据，这需要重写getView（）方法。系统在绘制列表的每一行的时候将调用此方法。
 * getView()有三个参数，position表示将显示的是第几行，covertView是从布局文件中inflate来的布局。
 * 我们用LayoutInflater的方法将定义好的vlist2.xml文件提取成View实例用来显示。然后将xml文件中的各个组件实例化
 * （简单的findViewById()方法）。这样便可以将数据对应到各个组件上了。但是按钮为了响应点击事件，
 * 需要为它添加点击监听器，这样就能捕获点击事件。至此一个自定义的listView就完成了，
 * 现在让我们回过头从新审视这个过程。系统要绘制ListView了，他首先获得要绘制的这个列表的长度，
 * 然后开始绘制第一行，怎么绘制呢？调用getView()函数。在这个函数里面首先获得一个View（实际上是一个ViewGroup），
 * 然后再实例并设置各个组件，显示之。好了，绘制完这一行了。那再绘制下一行，直到绘完为止。
 * 在实际的运行过程中会发现listView的每一行没有焦点了，这是因为Button抢夺了listView的焦点，
 * 只要布局文件中将Button设置为没有焦点就OK了
 * */