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
	
	// ListView ��ĳ�ѡ�к���߼�
	@Override       
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Log.v("ExtendBaseAdapter-click", (String)mData.get(position).get("title"));
	}
	
	/**
	 * * listview�е�����������Ի���
	 */
	public void showInfo(ViewHolder holder){
		new AlertDialog.Builder(this)
			.setTitle("" + holder.title.getText())
			.setMessage("" + holder.info.getText())
			.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
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
 * ���潫���������룬����ϸ�Ľ��ͣ�listView�ڿ�ʼ���Ƶ�ʱ��ϵͳ���ȵ���getCount����������
 * �������ķ���ֵ�õ�listView�ĳ��ȣ���Ҳ��Ϊʲô�ڿ�ʼ�ĵ�һ��ͼ�ر�ı���б��ȣ���
 * Ȼ�����������ȣ�����getView������һ����ÿһ�С�������getCount��������ֵ��0�Ļ���
 * �б�����ʾͬ��return 1����ֻ��ʾһ�С�ϵͳ��ʾ�б�ʱ������ʵ����һ�������������ｫʵ�����Զ��������������
 * ���ֶ��������ʱ�������ֶ�ӳ�����ݣ�����Ҫ��дgetView����������ϵͳ�ڻ����б��ÿһ�е�ʱ�򽫵��ô˷�����
 * getView()������������position��ʾ����ʾ���ǵڼ��У�covertView�ǴӲ����ļ���inflate���Ĳ��֡�
 * ������LayoutInflater�ķ���������õ�vlist2.xml�ļ���ȡ��Viewʵ��������ʾ��Ȼ��xml�ļ��еĸ������ʵ����
 * ���򵥵�findViewById()����������������Խ����ݶ�Ӧ������������ˡ����ǰ�ťΪ����Ӧ����¼���
 * ��ҪΪ����ӵ�����������������ܲ������¼�������һ���Զ����listView������ˣ�
 * ���������ǻع�ͷ��������������̡�ϵͳҪ����ListView�ˣ������Ȼ��Ҫ���Ƶ�����б�ĳ��ȣ�
 * Ȼ��ʼ���Ƶ�һ�У���ô�����أ�����getView()����������������������Ȼ��һ��View��ʵ������һ��ViewGroup����
 * Ȼ����ʵ�������ø����������ʾ֮�����ˣ���������һ���ˡ����ٻ�����һ�У�ֱ������Ϊֹ��
 * ��ʵ�ʵ����й����лᷢ��listView��ÿһ��û�н����ˣ�������ΪButton������listView�Ľ��㣬
 * ֻҪ�����ļ��н�Button����Ϊû�н����OK��
 * */