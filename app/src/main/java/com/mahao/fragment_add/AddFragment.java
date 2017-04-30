package com.mahao.fragment_add;



import android.R.integer;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
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
import com.mahao.provider.DiSanFangProvider;


/**
 * @妞ゅ湱娲伴崥锟� SQLiteDataBase
 * @閸掓稑缂撻懓锟� 妞诡剝璞�
 * @閸掓稑缂撻弮銉︽埂   2015楠烇拷12閺堬拷2閺冦儰绗傞崡锟�1:14:51
 *
 * @閹诲繗鍫�     TODO
 */
 public class AddFragment extends Fragment
{

	private Context context;
	private MyDb myDb;
	private EditText biaoTi;
	private EditText wangZhan;
	private EditText userName;
	private EditText passWord;
	private EditText beiZhu;
    private long id;
	
	public AddFragment()
	{
		
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		context = getActivity();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container, Bundle savedInstanceState) {
		
		    myDb = new MyDb(context);
		    LayoutInflater layout = inflater.from(context);
		    View view =layout.inflate(R.layout.crud,container, false);
		   
		    
		    biaoTi = (EditText)view.findViewById(R.id.et_biaoti);
		    wangZhan = (EditText)view.findViewById(R.id.et_wangzhan);
		    userName = (EditText)view.findViewById(R.id.et_username);
		    passWord = (EditText)view.findViewById(R.id.et_password);
		    beiZhu = (EditText)view.findViewById(R.id.et_beizhu);
		    Button dataAdd = (Button)view.findViewById(R.id.database_add);
			Button dataUpdate = (Button)view.findViewById(R.id.database_update); 
			
			dataUpdate.setVisibility(View.INVISIBLE);
			dataAdd.setVisibility(View.VISIBLE);
		     
		    dataAdd.setOnClickListener(new OnClickListener() {
		    	
				@Override
				public void onClick(View v) {
					
				    String mbiaoTi = biaoTi.getText().toString().trim(); 
				    String mwangZhan = wangZhan.getText().toString().trim(); 
				    String muserName = userName.getText().toString().trim(); 
				    String mpassWord = passWord.getText().toString().trim(); 
				    String mbeiZhu = beiZhu.getText().toString().trim();												
					
					SQLiteDatabase database = myDb.getWritableDatabase();
					ContentValues values = new ContentValues();
					
					values.put("titile",mbiaoTi);
					values.put("website",mwangZhan);
					values.put("username",muserName);
					values.put("password",mpassWord);
					values.put("notices",mbeiZhu);
					
					if(TextUtils.isEmpty(mbiaoTi) || TextUtils.isEmpty(mwangZhan))
					{
						Toast.makeText(context,"标题和网址不能为空",Toast.LENGTH_LONG);
					}
					else
					{
						ContentResolver resolver = context.getContentResolver();
						//插入：
						Uri uri = Uri.parse("content://com.mahao/insert");

						Uri  newUri = resolver.insert(uri,values);
						Log.i("mahao",newUri.toString()+"....");

						
						long  newid = ContentUris.parseId(newUri);
						
						if(newid != -1 )
						{
							Toast.makeText(context,"添加成功",Toast.LENGTH_LONG).show();
						}
						else
						{
							Toast.makeText(context,"添加失败",Toast.LENGTH_LONG).show();
						}
					}
				  //fa.finish();
				}
			});
		   return view;
	}
}

 
 
 
 
 
 
 
 
 
 