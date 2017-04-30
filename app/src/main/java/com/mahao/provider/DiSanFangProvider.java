package com.mahao.provider;

import com.mahao.db.MyDb;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;


public class DiSanFangProvider  extends ContentProvider{

	

	public static UriMatcher uriMatcher = new UriMatcher(0);
	public static final int CODE_INSERT = 1;
	public static final int CODE_UPDATE = 2;
	public static final int CODE_DELETE = 3;
	public static final int CODE_QUERY = 4;
	private  MyDb  myDb;
	private  SQLiteDatabase db;
	
	
	static 
	{
		uriMatcher.addURI("com.mahao","insert",CODE_INSERT);
		uriMatcher.addURI("com.mahao","update",CODE_UPDATE);
		uriMatcher.addURI("com.mahao","delete",CODE_DELETE);
		uriMatcher.addURI("com.mahao","query",CODE_QUERY);
	}
	
	@Override
	public boolean onCreate() {
		Context context  = getContext();
		
		myDb = new MyDb(context);
		db = myDb.getWritableDatabase();
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		return null;
	}

	@Override
	public String getType(Uri uri) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {

		long len = db.insert("Key", null, values);
		// Uri newUri = ContentUris.withAppendedId(uri,len);
		Log.i("mahao",len+"....");
		return Uri.parse(uri +"/" +len) ;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {

		int len = db.update("Key",values,selection,selectionArgs);
		return len;
	}

}
