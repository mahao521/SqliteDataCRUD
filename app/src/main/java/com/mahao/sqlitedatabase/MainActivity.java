package com.mahao.sqlitedatabase;

import android.R.id;
import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.mahao.R;
import com.mahao.adapter.DatabaseBaseAdapter;
import com.mahao.crud.DealWithDbActivity;
import com.mahao.db.MyDb;

public class MainActivity extends Activity // implements OnItemClickListener
{

	private ListView listView;
	//private SimpleCursorAdapter adapter ;
	private DatabaseBaseAdapter adapter;
	private MyDb myDb ;
	Cursor cursor ;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView)this.findViewById(R.id.database_listview);

		/*adapter = new SimpleCursorAdapter(this,R.layout.listview_info,
				null,new String[]{"titile","website"},new int[]{R.id.txt_title,R.id.txt_website});*/
		
		/*adapter = new DatabaseBaseAdapter(this,R.layout.listview_info,
				null,new String[]{"titile","website"},new int[]{R.id.txt_title,R.id.txt_website});*/
		
		myDb = new MyDb(this);
		SQLiteDatabase database = myDb.getReadableDatabase();
		cursor = database.query("Key",null, 
                null,null,null,null,null);
		 adapter = new DatabaseBaseAdapter(this,cursor);
		
	    listView.setAdapter(adapter);
	    
	     // listView.setOnItemClickListener(this);
	    listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				int id1 = (int)id;
	
				Intent intent = new Intent(MainActivity.this,DealWithDbActivity.class);
				intent.putExtra("name",id1);
				startActivity(intent);
			}
		});
	    
	    myDb = new MyDb(this);
	}
	
	public void  sqliteClick(View view)
	{
		Intent intent = new Intent(this,DealWithDbActivity.class);
		intent.putExtra("name",-1);
		startActivity(intent);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		SQLiteDatabase database = myDb.getReadableDatabase();
	    cursor = database.query("Key",null, 
			                 null,null,null,null,null);
		adapter = new DatabaseBaseAdapter(this,cursor);
		listView.setAdapter(adapter);
	    adapter.changeCursor(cursor);
	}	
	
	public void dataDelete(View view)
	{
		
        SQLiteDatabase database = myDb.getWritableDatabase();
        
        String id = (String)view.getTag();
        Toast.makeText(this,"���Ҫɾ����?.........."+id,Toast.LENGTH_LONG).show();
        int len = database.delete("Key","_id=?",new String[]{""+id});
        if(len == 0)
        {
        	Toast.makeText(this,"删除失败", Toast.LENGTH_LONG).show();
        }else {
			Toast.makeText(this,"删除成功",Toast.LENGTH_SHORT).show();
		}
        
        MainActivity.this.onResume();
	}
	
	@Override
	protected void onDestroy() {
		cursor.close();
		super.onDestroy();
	}
	
}
