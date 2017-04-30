package com.mahao.crud;

import com.mahao.R;
import com.mahao.fragement_update.UpdateFragment;
import com.mahao.fragment_add.AddFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * @妞ゅ湱娲伴崥锟� SQLiteDataBase
 * @閸掓稑缂撻懓锟� 妞诡剝璞�
 * @閸掓稑缂撻弮銉︽埂   2015楠烇拷12閺堬拷2閺冦儰绗傞崡锟�12:32:15
 *
 * @閹诲繗鍫�     TODO
 */
public class DealWithDbActivity extends FragmentActivity implements OnCheckedChangeListener
{
	
	private RadioButton add,update;
	private RadioGroup rgGroup;
	private AddFragment fragmentAdd ;
	private UpdateFragment fragmentUpdate;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.containsfragment);
		
		add = (RadioButton)this.findViewById(R.id.radio_add);
		update = (RadioButton)this.findViewById(R.id.radio_update);
		rgGroup = (RadioGroup)this.findViewById(R.id.radioGroup1);
		
		Intent intent = getIntent();
        int  id = (int) intent.getIntExtra("name",-2);
        
		rgGroup.setOnCheckedChangeListener(this);
		fragmentAdd = new AddFragment();
	    fragmentUpdate= new UpdateFragment(this,id);
	    
        FragmentManager  frm = getSupportFragmentManager();
		FragmentTransaction ft  =  frm.beginTransaction();
		
		Log.i("mahao", id+",,,,,,,,,,,,,,,,,,,");
		
        if(id < 0)
        {
        	ft.replace(R.id.sql_fragment,fragmentAdd);
        	update.setVisibility(View.INVISIBLE);
        	update.setFocusable(false);
        	add.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        }
        else
        {
        	 ft.replace(R.id.sql_fragment,fragmentUpdate);
        }
		ft.commit();
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {

		//Fragment fr = new Fragment();
		//FragmentManager  frm = fr.getFragmentManager();
		FragmentManager  frm = getSupportFragmentManager();
		FragmentTransaction ft  =  frm.beginTransaction();
		
		switch(checkedId)
		{
		  case R.id.radio_add:
			 ft.replace(R.id.sql_fragment,fragmentAdd);
			 View view = fragmentAdd.getView();			 
			 break;
			 
		  case R.id.radio_update:
			 ft.replace(R.id.sql_fragment,fragmentUpdate);
			 break; 
		}
		ft.commit();
	}
}
