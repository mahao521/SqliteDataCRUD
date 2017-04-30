package com.mahao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @椤圭洰鍚� SQLiteDataBase
 * @鍒涘缓鑰� 椹豹
 * @鍒涘缓鏃ユ湡   2015骞�12鏈�2鏃ヤ笂鍗�12:25:17
 *
 * @鎻忚堪     TODO
 */
public class MyDb extends SQLiteOpenHelper
{
	private static final String TAG = "MyDb";
	private static final String KEY_DATABASE= "create table Key(" +
			"_id integer primary key autoincrement," +
			"titile text," +
			"website text," +
			"username varchar(10)," +
			"password varchar(10)," +
			"notices text"+
			")";
	
	public MyDb(Context context) {
		super(context, "mahao.db",null, 3);
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(KEY_DATABASE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		Log.i(TAG,"鏁版嵁搴撴洿鏂颁簡");
	}
}
