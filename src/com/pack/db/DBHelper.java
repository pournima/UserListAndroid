package com.pack.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    
    private static final String DATABASE_NAME = "Database";
    public static final String DATABASE_TABLE = "userlist";
    public static final String DUMMY_DATABASE_TABLE = "userdetails";
    private static final int DATABASE_VERSION = 1;

    
    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
        
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String DATABASE_CREATE;
		   DATABASE_CREATE =
		            "create table userlist (_id integer primary key autoincrement, "
		            + "name text not null, age text not null);";
		 
		   db.execSQL(DATABASE_CREATE);
		   
		   DATABASE_CREATE=null;
		   
		   DATABASE_CREATE =
	            "create table userdetails (_id integer primary key autoincrement, "
	            + "name text not null, age text not null, gender text not null, hobby text not null);";
	    
		   db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	       db.execSQL("DROP TABLE IF EXISTS" +DATABASE_TABLE);
           onCreate(db);
           
	}

	  
	  
}
