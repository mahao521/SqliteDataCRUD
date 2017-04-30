package com.mahao.adapter;

import org.w3c.dom.Text;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mahao.R;

public class DatabaseBaseAdapter  extends CursorAdapter{
	
	public DatabaseBaseAdapter(Context context, Cursor c) {
		super(context, c);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		
		LayoutInflater infalater = LayoutInflater.from(context);
		View view = infalater.inflate(R.layout.listview_info, parent,false);
		
		TextView title = (TextView)view.findViewById(R.id.txt_title);
		TextView website = (TextView)view.findViewById(R.id.txt_website);
		
		Log.i("mahao", cursor.getCount()+"............."+cursor.getPosition());
	
		int indexTi = cursor.getColumnIndex("titile");
		int indexWeb = cursor.getColumnIndex("website");

		String mtitle = cursor.getString(indexTi);
		String mwebSite = cursor.getString(indexWeb);
		title.setText(mtitle);
		website.setText(mwebSite);
		
		Button button = (Button)view.findViewById(R.id.database_desc);
		int indexId = cursor.getColumnIndex("_id");
		String id = cursor.getString(indexId); 
		button.setTag(id);
		
		return view;
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		
		
	}
}
