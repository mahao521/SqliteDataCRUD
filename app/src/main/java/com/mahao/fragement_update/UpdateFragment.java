package com.mahao.fragement_update;

import android.R.integer;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.inputmethodservice.Keyboard.Row;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mahao.R;
import com.mahao.db.MyDb;




/**
 * @妞ゅ湱娲伴崥锟� SQLiteDataBase
 * @閸掓稑缂撻懓锟� 妞诡剝璞�
 * @閸掓稑缂撻弮銉︽埂   2015楠烇拷12閺堬拷2閺冦儰绗傞崡锟�1:15:21
 *
 * @閹诲繗鍫�     TODO
 */
public class UpdateFragment extends Fragment
{
    private Context context;
    private int id;
    
    private MyDb myDb;
	private EditText biaoTi;
	private EditText wangZhan;
	private EditText userName;
	private EditText passWord;
	private EditText beiZhu;
    private Cursor cursor;

	public UpdateFragment(){

     }
    
	public UpdateFragment(Context contex,int  id)
	{

		this.id = id;
		myDb = new MyDb(contex);
		
	}


	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		context = getActivity();
	}



	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		
		
		  View view = inflater.from(context).inflate(R.layout.crud, container,false);
		  
		  SQLiteDatabase  database = myDb.getWritableDatabase();
		  cursor  = database.query("Key",null,"_id = ?", new String[]{id+""},null,null,null);
		  
		  biaoTi = (EditText)view.findViewById(R.id.et_biaoti);
		  wangZhan = (EditText)view.findViewById(R.id.et_wangzhan);
		  userName = (EditText)view.findViewById(R.id.et_username);
		  passWord = (EditText)view.findViewById(R.id.et_password);
		  beiZhu = (EditText)view.findViewById(R.id.et_beizhu);
		  Button dataUpdate = (Button)view.findViewById(R.id.database_update); 
		  Button dataAdd = (Button)view.findViewById(R.id.database_add);
		   
		  dataUpdate.setVisibility(View.VISIBLE);
		  dataAdd.setVisibility(View.INVISIBLE);
		  
		  int a1 = cursor.getColumnIndex("titile");
		  int a2 = cursor.getColumnIndex("website");
		  
		    while(cursor.moveToNext())
		    {
				biaoTi.setText(cursor.getString(cursor.getColumnIndex("titile")));
				wangZhan.setText(cursor.getString(cursor.getColumnIndex("website")));
				userName.setText(cursor.getString(cursor.getColumnIndex("username")));
				passWord.setText(cursor.getString(cursor.getColumnIndex("password")));
				beiZhu.setText(cursor.getString(cursor.getColumnIndex("notices")));
			}
	
		   dataUpdate.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
				
				 String mbiaoTi = biaoTi.getText().toString().trim(); 
				 String mwangZhan = wangZhan.getText().toString().trim(); 
				 String muserName = userName.getText().toString().trim(); 
				 String mpassWord = passWord.getText().toString().trim(); 
				 String mbeiZhu = beiZhu.getText().toString().trim();
					
				ContentValues values = new ContentValues();
				values.put("titile", mbiaoTi);
				values.put("website", mwangZhan);
				values.put("username", muserName);
				values.put("password", mpassWord);
				values.put("notices", mbeiZhu);
				
			    ContentResolver resolver = getActivity().getContentResolver();
			    Uri uri = Uri.parse("content://com.mahao/update");
			    int len =  resolver.update(uri, values,null,null);
				Toast.makeText(getActivity(),"..."+len,Toast.LENGTH_SHORT).show();
				
				if(len > 0)
				  {
					Toast.makeText(context,"修改成功",Toast.LENGTH_LONG).show();
				  }
				else
				 {
					Toast.makeText(context,"修改失败",Toast.LENGTH_LONG).show();
				 }
			  }
			});
	       return view;
	 }
/*	@Override
	public void onDestroy()
	{
		cursor.close();
		super.onDestroy();
	}*/
}






